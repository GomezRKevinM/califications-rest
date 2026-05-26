package co.udc.desarrollo.web.calificationsRest.domain.exceptions.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InvalidStudentNameException extends DomainException {

    private static final String MESSAGE_BLANK = "El nombre del estudiante no puede estar vacío.";

    private InvalidStudentNameException(String message) {
        super(message);
    }

    public static InvalidStudentNameException becauseValueIsEmpty() {
        return new InvalidStudentNameException(MESSAGE_BLANK);
    }

    public static InvalidStudentNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidStudentNameException(String.format("El nombre del estudiante debe tener al menos %d caracteres", minimumLength));
    }
}
