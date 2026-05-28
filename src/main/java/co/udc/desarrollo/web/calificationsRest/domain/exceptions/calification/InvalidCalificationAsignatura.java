package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationAsignatura extends DomainException {

    private static final String EMPTY = "la asignatura no puede estar vacía";

    private InvalidCalificationAsignatura(String message) {
        super(message);
    }

    public static InvalidCalificationAsignatura becauseIsEmpty() {
        return new  InvalidCalificationAsignatura(EMPTY);
    }
}
