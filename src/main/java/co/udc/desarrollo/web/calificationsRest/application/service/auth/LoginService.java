package co.udc.desarrollo.web.calificationsRest.application.service.auth;

import co.udc.desarrollo.web.calificationsRest.application.port.in.auth.LoginUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.email.GetUserByEmailPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.auth.LoginCommand;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.auth.InvalidCredentialsException;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final GetUserByEmailPort getUserByEmailPort;
    private final Validator validator;

    @Override
    public UserModel execute(final LoginCommand command) {
        validateCommand(command);

        final UserEmail email = new UserEmail(command.email());
        final UserModel user = findUserOrFailWithInvalidCredentials(email);

        verifyPasswordOrFail(command.password(), user);
        ensureUserIsActiveOrFail(user);

        return user;
    }

    private void validateCommand(final LoginCommand command) {
        final Set<ConstraintViolation<LoginCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private UserModel findUserOrFailWithInvalidCredentials(final UserEmail email) {
        return getUserByEmailPort
                .getByEmail(email)
                .orElseThrow(InvalidCredentialsException::becauseCredentialsAreInvalid);
    }

    private static void verifyPasswordOrFail(final String plainPassword, final UserModel user) {
        if (!user.getPassword().verifyPlain(plainPassword)) {
            throw InvalidCredentialsException.becauseCredentialsAreInvalid();
        }
    }

    private static void ensureUserIsActiveOrFail(final UserModel user) {
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw InvalidCredentialsException.becauseUserIsNotActive();
        }
    }

}
