package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationId extends DomainException {
    private static final String MESSAGE_BLANK = "el id de calificación no puede estar vacío";

    private InvalidCalificationId(String message) {
        super(message);
    }

    public static InvalidCalificationId becauseIsEmpty(){
        return new InvalidCalificationId(MESSAGE_BLANK);
    }

}
