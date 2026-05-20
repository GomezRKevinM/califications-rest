package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.in.user.CreateUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.email.GetUserByEmailPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.SaveUserPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.CreateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.email.EmailNotificationService;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.UserApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserAlreadyExistsException;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;

@Log
@RequiredArgsConstructor
public final class CreateUserService implements CreateUserUseCase {

    private final SaveUserPort saveUserPort;
    private final GetUserByEmailPort getUserByEmailPort;
    private final EmailNotificationService emailNotificationService;
    private final Validator validator;

    @Override
    public UserModel execute(final CreateUserCommand command) {
        validateCommand(command);

        final UserEmail email = new UserEmail(command.email());
        ensureEmailIsNotTaken(email);

        final UserModel userToSave = UserApplicationMapper.fromCreateCommandToModel(command);
        final UserModel savedUser = saveUserPort.save(userToSave);

        emailNotificationService.notifyUserCreated(savedUser, command.password());

        return savedUser;
    }

    private void validateCommand(final CreateUserCommand command) {
        final Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureEmailIsNotTaken(final UserEmail email) {
        getUserByEmailPort
                .getByEmail(email)
                .ifPresent(
                        ignored -> {
                            throw UserAlreadyExistsException.becauseEmailAlreadyExists(email.value());
                        });
    }
}
