package co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.student.InvalidStudentLastNameException;

public record StudentLastName(String value) {

    public StudentLastName{
        String normalized = value.trim();

        if(normalized.isBlank() || normalized.isEmpty()){
            throw InvalidStudentLastNameException.becauseValueIsEmpty();
        }

        if(normalized.length() < 3){
            throw InvalidStudentLastNameException.becauseLengthIsTooShort(3);
        }

        value = normalized;
    }

    @Override
    public String toString(){
        return value;
    }
}
