package co.udc.desarrollo.web.calificationsRest.application.port.in.calification;

import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;

import java.util.List;

public interface GetAllCalificationUseCase {
    List<CalificationModel> execute();
}
