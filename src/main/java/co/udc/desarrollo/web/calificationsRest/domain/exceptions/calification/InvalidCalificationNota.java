package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationNota extends DomainException {

    private static final String EMPTY = "La nota no puede estar vacía";
    private static final String INVALID_FORMAT = "La nota solo acepta caracteres numericos";
    private static final String INVALID_VALUE = "La nota acepta minimo %d y maximo %d";
    private InvalidCalificationNota(String message) {
        super(message);
    }

    public static InvalidCalificationNota becauseIsNotValid(int min, int max){
        return new InvalidCalificationNota(String.format(INVALID_VALUE, min, max));
    }

    public static InvalidCalificationNota becauseIsEmpty(){
        return new InvalidCalificationNota(EMPTY);
    }

    public static InvalidCalificationNota invalidFormat(){
        return new InvalidCalificationNota(INVALID_FORMAT);
    }
}
