package co.udc.desarrollo.web.calificationsRest.domain.exceptions.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.DomainException;

public final class InInvalidStudentNameException extends DomainException {

    private static final String MESSAGE_BLANK = "El nombre del estudiante no puede estar vacío.";

    private InInvalidStudentNameException(String message) {
        super(message);
    }

    public static InInvalidStudentNameException becauseValueIsEmpty() {
        return new InInvalidStudentNameException(MESSAGE_BLANK);
    }

    public static InInvalidStudentNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InInvalidStudentNameException(String.format("El nombre del estudiante debe tener al menos %d caracteres", minimumLength));
    }
}
