package co.udc.desarrollo.web.calificationsRest.application.port.in.student;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetStudentByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetStudentByIdUseCase {
    StudentModel execute(@NotNull @Valid GetStudentByIdQuery query);
}
