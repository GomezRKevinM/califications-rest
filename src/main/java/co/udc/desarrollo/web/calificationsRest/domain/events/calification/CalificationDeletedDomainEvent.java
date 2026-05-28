package co.udc.desarrollo.web.calificationsRest.domain.events.calification;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.CalificationId;

import java.util.Map;

public final class CalificationDeletedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "calification.deleted";
    private final CalificationId calificationId;

    protected CalificationDeletedDomainEvent(final CalificationId calificationId) {
        super(EVENT_NAME);
        this.calificationId = calificationId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("id", calificationId.value());
    }
}
