package co.udc.desarrollo.web.calificationsRest.application.port.out.student;

import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;

import java.util.Optional;

public interface GetStudentByIdPort {
    Optional<StudentModel> getById(StudentId id);
}
