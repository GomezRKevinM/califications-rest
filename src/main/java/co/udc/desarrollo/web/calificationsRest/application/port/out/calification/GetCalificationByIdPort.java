package co.udc.desarrollo.web.calificationsRest.application.port.out.calification;

import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.CalificationId;

import java.util.Optional;

public interface GetCalificationByIdPort {
    Optional<CalificationModel> getById(CalificationId calificationId);
}
