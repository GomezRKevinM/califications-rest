package co.udc.desarrollo.web.calificationsRest.domain.events.student;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import lombok.Getter;

import java.util.Map;

@Getter
public final class StudentUpdatedDomainEvent extends DomainEvent {
    private static final String EVENT_NAME = "student.updated";

    private final StudentModel student;

    public StudentUpdatedDomainEvent(final StudentModel student) {
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
