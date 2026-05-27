package co.udc.desarrollo.web.calificationsRest.domain.exceptions.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class StudentNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "El estudiante con el id '%s' no existe.";

    private StudentNotFoundException(final String message) {
        super(message);
    }

    public static StudentNotFoundException becauseIdWasNotFound(final String studentId) {
        return new StudentNotFoundException(String.format(MESSAGE_BY_ID, studentId));
    }
}
