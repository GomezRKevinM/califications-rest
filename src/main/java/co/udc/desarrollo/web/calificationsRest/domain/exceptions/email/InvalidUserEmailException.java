package co.udc.desarrollo.web.calificationsRest.domain.exceptions.email;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidUserEmailException extends DomainException {
    private static final String MESSAGE_EMPTY = "El correo del usuario no puede estar vacío.";
    private static final String MESSAGE_INVALID_FORMAT = "El formato del correo es invalido: '%s'.";

    private InvalidUserEmailException(final String message) {
        super(message);
    }

    public static InvalidUserEmailException becauseValueIsEmpty() {
        return new InvalidUserEmailException(MESSAGE_EMPTY);
    }

    public static InvalidUserEmailException becauseFormatIsInvalid(final String email) {
        return new InvalidUserEmailException(String.format(MESSAGE_INVALID_FORMAT, email));
    }
}
