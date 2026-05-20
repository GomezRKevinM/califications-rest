package co.udc.desarrollo.web.calificationsRest.application.port.in.user;

import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetUserByIdUseCase {
    UserModel execute(@NotNull @Valid GetUserByIdUseCase query);
}
