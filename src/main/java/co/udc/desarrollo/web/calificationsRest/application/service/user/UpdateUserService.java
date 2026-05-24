package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.in.user.UpdateUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.email.GetUserByEmailPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetUserByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.UpdateUserPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.UpdateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.email.EmailNotificationService;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.UserApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserAlreadyExistsException;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserNotFoundException;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UpdateUserPort updateUserPort;
    private final GetUserByIdPort getUserByIdPort;
    private final GetUserByEmailPort getUserByEmailPort;
    private final EmailNotificationService emailNotificationService;
    private final Validator validator;

    @Override
    public UserModel execute(final UpdateUserCommand command) {
        validateCommand(command);

        final UserId userId = new UserId(command.id());
        final UserModel current = findExistingUserOrFail(userId);
        final UserEmail newEmail = new UserEmail(command.email());

        ensureEmailIsNotTakenByAnotherUser(newEmail, userId);

        final UserModel userToUpdate =
                UserApplicationMapper.fromUpdateCommandToModel(command, current.getPassword());
        if (hasSameState(current, command, userToUpdate)) {
            return current;
        }

        final UserModel updatedUser = updateUserPort.update(userToUpdate);

        emailNotificationService.notifyUserUpdated(updatedUser);

        return updatedUser;
    }

    private void validateCommand(final UpdateUserCommand command) {
        final Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private UserModel findExistingUserOrFail(final UserId userId) {
        return getUserByIdPort
                .getById(userId)
                .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
    }

    private void ensureEmailIsNotTakenByAnotherUser(final UserEmail newEmail, final UserId ownerId) {
        getUserByEmailPort
                .getByEmail(newEmail)
                .ifPresent(
                        found -> {
                            if (!found.getId().equals(ownerId)) {
                                throw UserAlreadyExistsException.becauseEmailAlreadyExists(newEmail.value());
                            }
                        }
                );
    }

    private boolean hasSameState(
            final UserModel current,
            final UpdateUserCommand command,
            final UserModel candidate) {
        return current.getName().equals(candidate.getName())
                && current.getEmail().equals(candidate.getEmail())
                && current.getRole() == candidate.getRole()
                && current.getStatus() == candidate.getStatus()
                && hasSamePassword(current, command);
    }

    private boolean hasSamePassword(final UserModel current, final UpdateUserCommand command) {
        if (Objects.isNull(command.password()) || command.password().isBlank()) {
            return true;
        }
        return current.getPassword().verifyPlain(command.password());
    }

}
