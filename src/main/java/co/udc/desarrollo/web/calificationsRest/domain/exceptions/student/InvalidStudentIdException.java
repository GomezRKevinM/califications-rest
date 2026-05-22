package co.udc.desarrollo.web.calificationsRest.domain.exceptions.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidStudentIdException extends DomainException {

    private static final String MESSAGE_BLANK = "El id del estudiante no puede estar vacío.";

    private InvalidStudentIdException(String message) {
        super(message);
    }

    public static InvalidStudentIdException becauseValueIsEmpty() {
        return new InvalidStudentIdException(MESSAGE_BLANK);
    }

}
