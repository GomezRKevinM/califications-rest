package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.in.user.GetAllUsersUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetAllUsersPort;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllUsersService implements GetAllUsersUseCase {

    private final GetAllUsersPort getAllUsersPort;

    @Override
    public List<UserModel> execute() {
        return getAllUsersPort.getAll();
    }

}
