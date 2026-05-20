package co.udc.desarrollo.web.calificationsRest.domain.exceptions.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidUserRoleException extends DomainException {
    private static final String MESSAGE_INVALID = "El rol de usuario '%s' no es valido.";

    private InvalidUserRoleException(final String message) {
        super(message);
    }

    public static InvalidUserRoleException becauseValueIsInvalid(final String role) {
        return new InvalidUserRoleException(String.format(MESSAGE_INVALID, role));
    }
}
