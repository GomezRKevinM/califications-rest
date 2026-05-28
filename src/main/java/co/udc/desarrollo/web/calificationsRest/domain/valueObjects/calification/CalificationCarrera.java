package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationCarrera;

public record CalificationCarrera(String value) {

    public CalificationCarrera{
        String normalized = value.trim();

        if(normalized.isEmpty()){
            throw InvalidCalificationCarrera.becauseIsEmpty();
        }

        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
