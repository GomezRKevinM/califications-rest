package co.udc.desarrollo.web.calificationsRest.application.port.out.user;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;

public interface DeleteUserPort {
    void delete(UserId userId);
}
