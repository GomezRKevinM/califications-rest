package co.udc.desarrollo.web.calificationsRest.application.port.out.student;


import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;

import java.util.List;

public interface GetAllStudentPort {
    List<StudentModel> getAll();
}
