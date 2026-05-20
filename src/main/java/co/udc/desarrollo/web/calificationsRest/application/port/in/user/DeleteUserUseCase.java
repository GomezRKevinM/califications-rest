package co.udc.desarrollo.web.calificationsRest.application.port.in.user;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteUserUseCase {
    void execute(@NotNull @Valid DeleteUserCommand command);
}
