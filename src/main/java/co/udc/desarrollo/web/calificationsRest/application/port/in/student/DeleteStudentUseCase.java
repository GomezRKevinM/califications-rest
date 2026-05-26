package co.udc.desarrollo.web.calificationsRest.application.port.in.student;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.DeleteStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user.DeleteUserResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteStudentUseCase {
    DeleteUserResult execute(@NotNull @Valid DeleteStudentCommand command);
}
