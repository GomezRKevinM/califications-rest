package co.udc.desarrollo.web.calificationsRest.application.port.in.student;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.CreateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateStudentUseCase {
    UserModel execute(@NotNull @Valid CreateStudentCommand command);
}
