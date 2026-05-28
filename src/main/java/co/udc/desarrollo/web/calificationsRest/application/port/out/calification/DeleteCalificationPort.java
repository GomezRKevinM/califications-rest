package co.udc.desarrollo.web.calificationsRest.application.port.out.calification;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.CalificationId;

public interface DeleteCalificationPort {
    void delete(CalificationId id);
}
