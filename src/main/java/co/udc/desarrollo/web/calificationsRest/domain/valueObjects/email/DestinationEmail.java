package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.email;

import java.util.Objects;

public final record DestinationEmail(String value) {

    public DestinationEmail {
        validateNotBlank(value,"El correo del destinatario es requerido.");
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
