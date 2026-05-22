package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentName;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.sql.Timestamp;

@Value
@RequiredArgsConstructor
public class StudentModel {

    StudentId id;
    StudentName name;
    Timestamp created_at;
    Timestamp update_at;

    public StudentModel(StudentId id, StudentName name) {
        this(id, name, null, null);
    }
}
