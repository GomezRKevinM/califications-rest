package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationActividadEvaluada extends DomainException {

    private static final String EMPTY = "la actividad evaluada no puede estar vacía";

    private InvalidCalificationActividadEvaluada(String message) {
        super(message);
    }

    public static InvalidCalificationActividadEvaluada becauseIsEmpty(){
        return new InvalidCalificationActividadEvaluada(EMPTY);
    }
}
