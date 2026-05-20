package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.InvalidUserNameException;

import java.util.Objects;

public record UserName(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public UserName {
        final String normalizedValue = Objects.requireNonNull(value, "el username no puede ser nulo").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidUserNameException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidUserNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
