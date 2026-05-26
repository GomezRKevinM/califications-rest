package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.student.InvalidStudentNameException;

public record StudentName(String value) {
    public StudentName{
        String normalized = value.trim();

        if(normalized.isBlank() || normalized.isEmpty()){
            throw InvalidStudentNameException.becauseValueIsEmpty();
        }

        if(normalized.length() < 3){
            throw InvalidStudentNameException.becauseLengthIsTooShort(3);
        }
        value = normalized;
    }
}
