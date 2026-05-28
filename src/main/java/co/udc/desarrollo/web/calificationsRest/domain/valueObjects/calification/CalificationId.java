package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationId;

public record CalificationId(String value) {

    public CalificationId{
        String normalized = value.trim();

        if (normalized.isEmpty()) {
            throw InvalidCalificationId.becauseIsEmpty();
        }

        value = normalized;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
