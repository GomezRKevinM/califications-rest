package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.InvalidCalificationPorcentaje;

public record CalificationNota (double value){

    public CalificationNota{
        if(value < 0 || value > 5) throw InvalidCalificationPorcentaje.becauseIsNotValid(0,5);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
