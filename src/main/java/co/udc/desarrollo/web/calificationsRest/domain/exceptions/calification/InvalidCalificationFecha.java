package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationFecha extends DomainException {

    private static final String MESSAGE_BLANK = "La fecha no puede estar vacía";
    private static final String INVALID_FORMAT = "Formato invalido para la fecha";

    private InvalidCalificationFecha(String message) {
        super(message);
    }

    public static InvalidCalificationFecha becauseIsNull()
    {
        return new InvalidCalificationFecha(MESSAGE_BLANK);
    }

    public static InvalidCalificationFecha invalidFormat(){
        return new InvalidCalificationFecha(INVALID_FORMAT);
    }
}
