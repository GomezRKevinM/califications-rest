package co.udc.desarrollo.web.calificationsRest.domain.enums.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.InvalidUserStatusException;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    BLOCKED;

    public static UserStatus fromString(final String value) {
        for (final UserStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw InvalidUserStatusException.becauseValueIsInvalid(value);
    }
}
