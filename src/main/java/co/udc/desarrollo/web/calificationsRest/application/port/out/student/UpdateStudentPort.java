package co.udc.desarrollo.web.calificationsRest.application.port.out.student;

import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;

public interface UpdateStudentPort {
    StudentModel update(StudentModel student);
}
