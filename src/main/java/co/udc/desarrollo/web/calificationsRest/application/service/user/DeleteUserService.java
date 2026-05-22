package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.in.user.DeleteUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.DeleteUserPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetUserByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.UserApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserNotFoundException;
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
    private final Validator validator;

    @Override
    public void execute(final DeleteUserCommand command) {
        validateCommand(command);

        final UserId userId = UserApplicationMapper.fromDeleteCommandToUserId(command);
        ensureUserExists(userId);
        deleteUserPort.delete(userId);
    }

    private void validateCommand(final DeleteUserCommand command) {
        final Set<ConstraintViolation<DeleteUserCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureUserExists(final UserId userId) {
        getUserByIdPort
                .getById(userId)
                .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
    }

}
