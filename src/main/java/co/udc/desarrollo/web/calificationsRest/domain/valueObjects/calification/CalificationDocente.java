package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationDocente;

public record CalificationDocente(String value) {

    public CalificationDocente{
        String normalized = value.trim();

        if(normalized.isEmpty()){
            throw InvalidCalificationDocente.becauseIsEmpty();
        }

        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
