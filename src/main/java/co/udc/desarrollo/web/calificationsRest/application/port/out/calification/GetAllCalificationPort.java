package co.udc.desarrollo.web.calificationsRest.application.port.out.calification;

import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;

import java.util.List;

public interface GetAllCalificationPort {
    List<CalificationModel> getAll();
}
