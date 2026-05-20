package co.udc.desarrollo.web.calificationsRest.domain.enums.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.InvalidUserRoleException;

public enum UserRole {
    ADMIN,
    MEMBER,
    REVIEWER;

    public static UserRole fromString(final String value) {
        for (final UserRole role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw InvalidUserRoleException.becauseValueIsInvalid(value);
    }
}
