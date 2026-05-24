package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.out.user.DeleteUserPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetUserByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
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

class DeleteUserServiceTest {

    private final DeleteUserPort deleteUserPort = mock(DeleteUserPort.class);
    private final GetUserByIdPort getUserByIdPort = mock(GetUserByIdPort.class);
    private final EmailNotificationService emailNotificationService = mock(EmailNotificationService.class);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final DeleteUserService service =
            new DeleteUserService(deleteUserPort, getUserByIdPort, emailNotificationService, validator);

    @Test
    void executeDoesNothingWhenUserDoesNotExist() {
        final UserId userId = new UserId("user-1");
        when(getUserByIdPort.getById(userId)).thenReturn(Optional.empty());

        final var result = service.execute(new DeleteUserCommand(userId.value()));

        assertThat(result.deleted()).isFalse();
        assertThat(result.code()).isEqualTo("USER_NOT_FOUND");
        verify(deleteUserPort, never()).delete(userId);
        verify(emailNotificationService, never()).notifyUserDeleted(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void executeDeletesWhenUserExists() {
        final UserId userId = new UserId("user-1");
        final UserModel user = user(userId);
        when(getUserByIdPort.getById(userId)).thenReturn(Optional.of(user));

        final var result = service.execute(new DeleteUserCommand(userId.value()));

        assertThat(result.deleted()).isTrue();
        assertThat(result.code()).isEqualTo("USER_DELETED");
        verify(deleteUserPort).delete(userId);
        verify(emailNotificationService).notifyUserDeleted(user);
    }

    private static UserModel user(final UserId id) {
        return new UserModel(
                id,
                new UserName("Maria Gomez"),
                new UserEmail("maria@example.com"),
                UserPassword.fromHash("stored-hash"),
                UserRole.MEMBER,
                UserStatus.ACTIVE,
                "2026-05-24T10:00:00",
                "2026-05-24T10:00:00");
    }
}
