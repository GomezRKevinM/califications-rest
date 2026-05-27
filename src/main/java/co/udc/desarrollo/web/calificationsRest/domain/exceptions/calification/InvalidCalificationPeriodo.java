package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationPeriodo extends DomainException {

    private static final String EMPTY = "El periodo no puede estar vacío";
    private static final String INVALID_LEN = "En el periodo el tamaño minimo es de %d y el tamaño maximo es de %d";

    private InvalidCalificationPeriodo(String message) {
        super(message);
    }

    public static InvalidCalificationPeriodo becauseIsEmpty(){
        return new InvalidCalificationPeriodo(EMPTY);
    }

    public static  InvalidCalificationPeriodo becauseInvalidLen(int min, int max){
        return new InvalidCalificationPeriodo(String.format(INVALID_LEN, min, max));
    }
}
