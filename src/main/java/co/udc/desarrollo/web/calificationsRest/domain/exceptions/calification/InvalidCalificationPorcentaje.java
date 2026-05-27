package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationPorcentaje extends DomainException {

    private static final String EMPTY = "El porcentaje no puede estar vacío";
    private static final String ERROR_VALUE = "El valor minimo es %d y el valor maximo es %d";

    private InvalidCalificationPorcentaje(String message) {
        super(message);
    }

    public static InvalidCalificationPorcentaje becauseIsEmpty(){
        return new InvalidCalificationPorcentaje(EMPTY);
    }

    public static InvalidCalificationPorcentaje becauseIsNotValid(int min, int max){
        return new InvalidCalificationPorcentaje(String.format(ERROR_VALUE, min, max));
    }

}
