package co.udc.desarrollo.web.calificationsRest.application.port.out.calification;

import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;

public interface SaveCalificationPort {
    CalificationModel save(CalificationModel calification);
}
