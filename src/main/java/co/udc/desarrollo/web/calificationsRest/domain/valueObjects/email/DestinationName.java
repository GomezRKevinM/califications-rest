package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.email;

import java.util.Objects;

public record DestinationName(String value) {

    public DestinationName {
        validateNotBlank(value,"El nombre del destinatario es requerido.");
    }

    private static String validateNotBlank(final String value, final String errorMessage) {
        Objects.requireNonNull(value, errorMessage);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
