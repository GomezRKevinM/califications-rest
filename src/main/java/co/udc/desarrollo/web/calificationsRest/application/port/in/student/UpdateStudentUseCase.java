package co.udc.desarrollo.web.calificationsRest.application.port.in.student;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.UpdateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateStudentUseCase {
    StudentModel execute(@NotNull @Valid UpdateStudentCommand command);
}
