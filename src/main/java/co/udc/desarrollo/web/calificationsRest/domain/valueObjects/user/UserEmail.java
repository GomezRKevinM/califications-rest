package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.email.InvalidUserEmailException;

import java.util.Objects;
import java.util.regex.Pattern;

public record UserEmail(String value) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    public UserEmail {
        final String normalizedValue =
                Objects.requireNonNull(value, "UserEmail no puede ser nulo").trim().toLowerCase();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidUserEmailException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String normalizedValue) {
        if (!EMAIL_PATTERN.matcher(normalizedValue).matches()) {
            throw InvalidUserEmailException.becauseFormatIsInvalid(normalizedValue);
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
