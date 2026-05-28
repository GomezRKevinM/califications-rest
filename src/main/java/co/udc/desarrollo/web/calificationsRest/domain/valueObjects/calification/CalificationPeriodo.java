package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationPeriodo;

public record CalificationPeriodo(String value) {

    public CalificationPeriodo{
        String normalized = value.trim();

        if(normalized.isEmpty()) throw InvalidCalificationPeriodo.becauseIsEmpty();

        if(normalized.length() < 1 || normalized.length() > 3){
            throw InvalidCalificationPeriodo.becauseInvalidLen(1,3);
        }

        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
