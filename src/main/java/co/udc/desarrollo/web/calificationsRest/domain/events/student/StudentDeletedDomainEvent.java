package co.udc.desarrollo.web.calificationsRest.domain.events.student;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import lombok.Getter;

import java.util.Map;

@Getter
public final class StudentDeletedDomainEvent extends DomainEvent {
    private static final String EVENT_NAME = "student.deleted";

    private final StudentId studentId;

    public StudentDeletedDomainEvent(final UserId userId) {
        super(EVENT_NAME);
        this.studentId = getStudentId();
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("id", studentId.value());
    }
}
