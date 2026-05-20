package co.udc.desarrollo.web.calificationsRest.application.port.out.email;

import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import java.util.Optional;

public interface GetUserByEmailPort {
    Optional<UserModel> getByEmail(UserEmail email);
}
