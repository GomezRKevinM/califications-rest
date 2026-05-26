package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.student.InvalidStudentIdException;

public record StudentId(String value) {

    public StudentId{
        String normalized = value.trim();
        if(normalized.isBlank() || normalized.isEmpty()){
            throw InvalidStudentIdException.becauseValueIsEmpty();
        }

        value = normalized;
    }

    @Override
    public String toString(){
        return value;
    }
}
