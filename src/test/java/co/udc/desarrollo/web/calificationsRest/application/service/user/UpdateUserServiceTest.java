package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.out.email.GetUserByEmailPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetUserByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.UpdateUserPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.UpdateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.email.EmailNotificationService;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserRole;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserPassword;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateUserServiceTest {

    private final UpdateUserPort updateUserPort = mock(UpdateUserPort.class);
    private final GetUserByIdPort getUserByIdPort = mock(GetUserByIdPort.class);
    private final GetUserByEmailPort getUserByEmailPort = mock(GetUserByEmailPort.class);
    private final EmailNotificationService emailNotificationService = mock(EmailNotificationService.class);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final UpdateUserService service =
            new UpdateUserService(
                    updateUserPort,
                    getUserByIdPort,
                    getUserByEmailPort,
                    emailNotificationService,
                    validator);

    @Test
    void executeReturnsCurrentUserWhenPayloadDoesNotChangeState() {
        final UserModel current = user(UserPassword.fromPlainText("Secret123"));
        when(getUserByIdPort.getById(current.getId())).thenReturn(Optional.of(current));
        when(getUserByEmailPort.getByEmail(current.getEmail())).thenReturn(Optional.of(current));

        final UserModel result = service.execute(
                new UpdateUserCommand(
                        current.getId().value(),
                        current.getName().value(),
                        current.getEmail().value(),
                        null,
                        current.getRole().name(),
                        current.getStatus().name()));

        assertThat(result).isSameAs(current);
        verify(updateUserPort, never()).update(org.mockito.ArgumentMatchers.any());
        verify(emailNotificationService, never()).notifyUserUpdated(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void executeIsIdempotentWhenRepeatedPayloadContainsTheSamePlainPassword() {
        final UserModel current = user(UserPassword.fromPlainText("Secret123"));
        when(getUserByIdPort.getById(current.getId())).thenReturn(Optional.of(current));
        when(getUserByEmailPort.getByEmail(current.getEmail())).thenReturn(Optional.of(current));

        final UserModel result = service.execute(
                new UpdateUserCommand(
                        current.getId().value(),
                        current.getName().value(),
                        current.getEmail().value(),
                        "Secret123",
                        current.getRole().name(),
                        current.getStatus().name()));

        assertThat(result).isSameAs(current);
        verify(updateUserPort, never()).update(org.mockito.ArgumentMatchers.any());
        verify(emailNotificationService, never()).notifyUserUpdated(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void executeUpdatesAndNotifiesWhenStateChanges() {
        final UserModel current = user(UserPassword.fromPlainText("Secret123"));
        final UserModel updated = new UserModel(
                current.getId(),
                new UserName("Maria Actualizada"),
                current.getEmail(),
                current.getPassword(),
                current.getRole(),
                current.getStatus(),
                current.getCreatedAt(),
                "2026-05-24T11:00:00");
        when(getUserByIdPort.getById(current.getId())).thenReturn(Optional.of(current));
        when(getUserByEmailPort.getByEmail(current.getEmail())).thenReturn(Optional.of(current));
        when(updateUserPort.update(org.mockito.ArgumentMatchers.any())).thenReturn(updated);

        final UserModel result = service.execute(
                new UpdateUserCommand(
                        current.getId().value(),
                        "Maria Actualizada",
                        current.getEmail().value(),
                        null,
                        current.getRole().name(),
                        current.getStatus().name()));

        assertThat(result).isSameAs(updated);
        verify(updateUserPort).update(org.mockito.ArgumentMatchers.any());
        verify(emailNotificationService).notifyUserUpdated(updated);
    }

    private static UserModel user(final UserPassword password) {
        return new UserModel(
                new UserId("user-1"),
                new UserName("Maria Gomez"),
                new UserEmail("maria@example.com"),
                password,
                UserRole.MEMBER,
                UserStatus.ACTIVE,
                "2026-05-24T10:00:00",
                "2026-05-24T10:00:00");
    }
}
