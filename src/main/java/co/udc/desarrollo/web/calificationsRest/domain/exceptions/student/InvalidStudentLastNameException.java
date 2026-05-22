package co.udc.desarrollo.web.calificationsRest.domain.exceptions.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public class InvalidStudentLastNameException extends DomainException {

    private static final String MESSAGE_BLANK = "El apellido del estudiante no puede estar vacio.";

    private InvalidStudentLastNameException(String message) {
        super(message);
    }

    public static InvalidStudentLastNameException becauseValueIsEmpty() {
        return new InvalidStudentLastNameException(MESSAGE_BLANK);
    }

    public static InvalidStudentLastNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidStudentLastNameException(String.format("El apellido del estudiante debe tener al menos %d caracteres", minimumLength));
    }
}
