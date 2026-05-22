package co.udc.desarrollo.web.calificationsRest.application.port.out.student;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;

public interface DeleteStudentPort {
    void delete(StudentId studentId);
}
