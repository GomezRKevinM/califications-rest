package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.InvalidUserIdException;

import java.util.Objects;

public record UserId(String value) {

    public UserId {
        final String normalizedValue = Objects.requireNonNull(value, "UserId no puede ser nulo").trim();
        validateNotEmpty(normalizedValue);
        // asigna el valor normalizado al componente
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidUserIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
