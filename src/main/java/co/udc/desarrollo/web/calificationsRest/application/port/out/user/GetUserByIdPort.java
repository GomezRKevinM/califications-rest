package co.udc.desarrollo.web.calificationsRest.application.port.out.user;

import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;

import java.util.Optional;

public interface GetUserByIdPort {
    Optional<UserModel> getById(UserId userId);
}
