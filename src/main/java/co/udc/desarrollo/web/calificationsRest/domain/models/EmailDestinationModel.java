package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.email.Body;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.email.DestinationEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.email.DestinationName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.email.Subject;
import lombok.Value;

import java.util.Objects;

@Value
public class EmailDestinationModel {

    DestinationEmail destinationEmail;
    DestinationName destinationName;
    Subject subject;
    Body body;

    public EmailDestinationModel(
            final String destinationEmail,
            final String destinationName,
            final String subject,
            final String body) {
        this.destinationEmail = new DestinationEmail(destinationEmail);
        this.destinationName = new DestinationName(destinationName);

        this.subject = new Subject(subject);
        this.body = new Body(body);
    }

}
