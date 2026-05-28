package co.udc.desarrollo.web.calificationsRest.application.port.out.calification;

import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;

public interface UpdateCalificationPort {
    CalificationModel update(CalificationModel calification);
}
