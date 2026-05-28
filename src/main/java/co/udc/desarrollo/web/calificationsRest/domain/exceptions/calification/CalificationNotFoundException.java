package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class CalificationNotFoundException extends DomainException {
    private static final String NOT_FOUND_MESSAGE = "Calification %s not found";

    private CalificationNotFoundException(String message) {
        super(message);
    }

    public static CalificationNotFoundException NOT_FOUND(final String id){
        return new CalificationNotFoundException(String.format(NOT_FOUND_MESSAGE, id));
    }
}
