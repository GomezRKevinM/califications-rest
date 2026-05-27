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
    String created_at;
    String update_at;

    public StudentModel(StudentId id, StudentName name, StudentLastName lastName) {
        this(id, name,lastName, null, null);
    }

    public static StudentModel create(
            final StudentId id,
            final StudentName name,
            final StudentLastName lastName) {
        return new StudentModel(id, name, lastName );
    }
}
