package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationActividadEvaluada;

public record CalificationActividadEvaluada(String value) {

    public CalificationActividadEvaluada{
        String normalized = value.trim();

        if(normalized.isEmpty()) throw InvalidCalificationActividadEvaluada.becauseIsEmpty();

        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
