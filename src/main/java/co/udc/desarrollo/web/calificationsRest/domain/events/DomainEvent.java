package co.udc.desarrollo.web.calificationsRest.domain.events;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public abstract class DomainEvent {
    private final String eventName;
    private final LocalDateTime occurredOn;

    protected DomainEvent(final String eventName) {
        this.eventName = eventName;
        this.occurredOn = LocalDateTime.now();
    }

    public abstract Map<String, String> payload();

}
