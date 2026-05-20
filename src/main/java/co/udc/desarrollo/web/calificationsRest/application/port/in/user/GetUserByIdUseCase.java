package co.udc.desarrollo.web.calificationsRest.application.port.in.user;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetUserByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetUserByIdUseCase {
    UserModel execute(@NotNull @Valid GetUserByIdQuery query);
}
