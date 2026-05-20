package co.udc.desarrollo.web.calificationsRest.application.port.out.email;

import co.udc.desarrollo.web.calificationsRest.domain.models.EmailDestinationModel;

public interface EmailSenderPort {
    void send(EmailDestinationModel destination);
}
