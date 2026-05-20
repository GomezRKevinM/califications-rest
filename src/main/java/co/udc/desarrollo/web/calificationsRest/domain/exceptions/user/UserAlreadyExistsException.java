package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class UserAlreadyExistsException extends DomainException {

    private static final String MESSAGE_EMAIL_EXISTS = "Un usuario con el correo '%s' ya existe.";

    private UserAlreadyExistsException(final String message) {
        super(message);
    }

    public static UserAlreadyExistsException becauseEmailAlreadyExists(final String email) {
        return new UserAlreadyExistsException(String.format(MESSAGE_EMAIL_EXISTS, email));
    }

}
