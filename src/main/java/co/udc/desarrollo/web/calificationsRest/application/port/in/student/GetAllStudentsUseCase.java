package co.udc.desarrollo.web.calificationsRest.application.port.in.student;

import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;

import java.util.List;

public interface GetAllStudentsUseCase {
    List<StudentModel> execute();
}
