package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationDocente extends DomainException {

   private static final String EMPTY = "El docente no puede estar vacío";

    private InvalidCalificationDocente(String message)
    {
        super(message);
    }

    public InvalidCalificationDocente becauseIsEmpty(){
        return new InvalidCalificationDocente(EMPTY);
    }
}
