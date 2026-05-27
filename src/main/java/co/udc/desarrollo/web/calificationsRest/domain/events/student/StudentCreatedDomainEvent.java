package co.udc.desarrollo.web.calificationsRest.domain.events.student;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import lombok.Getter;

import java.util.Map;

@Getter
public final class StudentCreatedDomainEvent extends DomainEvent {
    private static final String EVENT_NAME = "student.created";

    private final StudentModel student;

    public StudentCreatedDomainEvent(final StudentModel student) {
        super(EVENT_NAME);
        this.student = student;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", student.getId().value(),
                "name", student.getName().value(),
                "lastName", student.getLastName().value()
        );
    }

}
