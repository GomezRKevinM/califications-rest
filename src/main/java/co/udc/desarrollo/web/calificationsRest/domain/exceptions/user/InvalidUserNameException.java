package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidUserNameException extends DomainException {
    private static final String MESSAGE_EMPTY = "El nombre de usuario no puede estar vacío.";
    private static final String MESSAGE_TOO_SHORT = "El nombre de usuario debe tener al menos %d caracteres.";

    private InvalidUserNameException(final String message) {
        super(message);
    }

    public static InvalidUserNameException becauseValueIsEmpty() {
        return new InvalidUserNameException(MESSAGE_EMPTY);
    }

    public static InvalidUserNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidUserNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }

}
