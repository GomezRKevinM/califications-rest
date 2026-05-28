package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationUniversidad;

public record CalificationUniversidad(String value) {

    public CalificationUniversidad{
        String normalized = value.trim();

        if(normalized.isEmpty()){
            throw InvalidCalificationUniversidad.becauseIsEmpty();
        }

        if(normalized.length()<3){
            throw InvalidCalificationUniversidad.isShort(3);
        }

        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
