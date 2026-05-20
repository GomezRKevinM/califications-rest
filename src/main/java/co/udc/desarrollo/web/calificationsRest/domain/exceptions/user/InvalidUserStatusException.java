package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidUserStatusException extends DomainException {
    private static final String MESSAGE_INVALID = "El estado del usuario '%s' no es valido.";

    private InvalidUserStatusException(final String message) {
        super(message);
    }

    public static InvalidUserStatusException becauseValueIsInvalid(final String status) {
        return new InvalidUserStatusException(String.format(MESSAGE_INVALID, status));
    }
}
