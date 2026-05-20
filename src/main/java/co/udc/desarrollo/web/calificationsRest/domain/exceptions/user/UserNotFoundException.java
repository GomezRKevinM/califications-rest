package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class UserNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "El usuario con el id '%s' no existe.";

    private UserNotFoundException(final String message) {
        super(message);
    }

    public static UserNotFoundException becauseIdWasNotFound(final String userId) {
        return new UserNotFoundException(String.format(MESSAGE_BY_ID, userId));
    }
}
