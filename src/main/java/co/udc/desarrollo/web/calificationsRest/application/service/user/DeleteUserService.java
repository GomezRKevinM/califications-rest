package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.in.user.DeleteUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.DeleteUserPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetUserByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user.DeleteUserResult;
import co.udc.desarrollo.web.calificationsRest.application.service.email.EmailNotificationService;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.UserApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;
    private final GetUserByIdPort getUserByIdPort;
    private final EmailNotificationService emailNotificationService;
    private final Validator validator;

    @Override
    public DeleteUserResult execute(final DeleteUserCommand command) {
        validateCommand(command);

        final UserId userId = UserApplicationMapper.fromDeleteCommandToUserId(command);
        return getUserByIdPort
                .getById(userId)
                .map(user -> deleteExistingUser(userId, user))
                .orElseGet(() -> DeleteUserResult.notFound(userId.value()));
    }

    private void validateCommand(final DeleteUserCommand command) {
        final Set<ConstraintViolation<DeleteUserCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private DeleteUserResult deleteExistingUser(final UserId userId, final UserModel user) {
        deleteUserPort.delete(userId);
        emailNotificationService.notifyUserDeleted(user);
        return DeleteUserResult.deleted(userId.value());
    }

}
