package co.udc.desarrollo.web.calificationsRest.application.port.out.user;

import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import java.util.List;

public interface GetAllUsersPort {
    List<UserModel> getAll();
}
