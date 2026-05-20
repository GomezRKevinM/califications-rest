package co.udc.desarrollo.web.calificationsRest.application.port.in.user;

import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;

import java.util.List;

public interface GetAllUsersUseCase {
    List<UserModel> execute();
}
