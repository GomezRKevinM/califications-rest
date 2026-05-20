package co.udc.desarrollo.web.calificationsRest.application.port.in.auth;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.auth.LoginCommand;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface LoginUseCase {
    UserModel execute(@NotNull @Valid LoginCommand command);
}
