package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidCalificationUniversidad extends DomainException {

    private static final String EMPTY = "la universidad no puede estar vacía";
    private static final String IS_SHORT = "la universidad debe tener al menos %d caracteres";

    private InvalidCalificationUniversidad(String message) {
        super(message);
    }

    public InvalidCalificationUniversidad becauseIsEmpty() {
        return new InvalidCalificationUniversidad(EMPTY);
    }

    public InvalidCalificationUniversidad isShort(final int min) {
        return  new InvalidCalificationUniversidad(String.format(IS_SHORT, min));
    }
}
