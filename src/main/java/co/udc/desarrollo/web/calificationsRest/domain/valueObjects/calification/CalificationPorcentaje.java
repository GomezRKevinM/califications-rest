package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationPorcentaje;

public record CalificationPorcentaje(double value) {

    public CalificationPorcentaje{
        String normalized = String.valueOf(value).trim();

        if(normalized.isEmpty()) throw InvalidCalificationPorcentaje.becauseIsEmpty();

        if(value > 1 || value < 0) throw InvalidCalificationPorcentaje.becauseIsNotValid(1,100);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
