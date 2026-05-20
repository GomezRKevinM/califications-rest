package co.udc.desarrollo.web.calificationsRest.application.port.out.user;

import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;

public interface UpdateUserPort {
    UserModel update(UserModel user);
}
