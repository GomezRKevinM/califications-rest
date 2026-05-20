package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidUserPasswordException extends DomainException {
    private static final String MESSAGE_EMPTY = "La contraseña no puede estar vacía.";
    private static final String MESSAGE_TOO_SHORT =
            "La contraseña debe tener al menos %d caracteres.";

    private InvalidUserPasswordException(final String message) {
        super(message);
    }

    public static InvalidUserPasswordException becauseValueIsEmpty() {
        return new InvalidUserPasswordException(MESSAGE_EMPTY);
    }

    public static InvalidUserPasswordException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidUserPasswordException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }

}
