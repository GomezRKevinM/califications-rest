package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidUserIdException extends DomainException {
    private static final String MESSAGE_EMPTY = "El id del usuario no puede estar vacío.";

    private InvalidUserIdException(final String message) {
        super(message);
    }

    public static InvalidUserIdException becauseValueIsEmpty() {
        return new InvalidUserIdException(MESSAGE_EMPTY);
    }

}
