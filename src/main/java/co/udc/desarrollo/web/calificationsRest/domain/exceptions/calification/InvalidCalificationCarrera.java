package co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification;

public class InvalidCalificationCarrera extends RuntimeException {

    private static final String EMPTY = "la carrera no puede estar vacia";

    private InvalidCalificationCarrera(String message) {
        super(message);
    }

    public InvalidCalificationCarrera becauseIsEmpty() {
        return new  InvalidCalificationCarrera(EMPTY);
    }
}
