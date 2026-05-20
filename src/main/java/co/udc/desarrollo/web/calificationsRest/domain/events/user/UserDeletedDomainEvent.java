package co.udc.desarrollo.web.calificationsRest.domain.events.user;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import lombok.Getter;

import java.util.Map;

@Getter
public final class UserDeletedDomainEvent extends DomainEvent {
    private static final String EVENT_NAME = "user.deleted";

    private final UserId userId;

    public UserDeletedDomainEvent(final UserId userId) {
        super(EVENT_NAME);
        this.userId = userId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("id", userId.value());
    }
}
