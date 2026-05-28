package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationAsignatura;

public record CalificationAsignatura(String value) {

    public CalificationAsignatura{
        String normalized = value.trim();

        if(normalized.isEmpty()){
            throw InvalidCalificationAsignatura.becauseIsEmpty();
        }

        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
