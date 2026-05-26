package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentLastName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentName;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.sql.Timestamp;

@Value
@RequiredArgsConstructor
public class StudentModel {

    StudentId id;
    StudentName name;
    StudentLastName lastName;
    Timestamp created_at;
    Timestamp update_at;

    public StudentModel(StudentId id, StudentName name, StudentLastName lastName) {
        this(id, name,lastName, null, null);
    }
}
