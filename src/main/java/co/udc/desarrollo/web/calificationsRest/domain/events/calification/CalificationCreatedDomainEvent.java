package co.udc.desarrollo.web.calificationsRest.domain.events.calification;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import lombok.Getter;

import java.util.Map;

@Getter
public final class CalificationCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "calification.created";
    private final CalificationModel calification;

    private CalificationCreatedDomainEvent(final CalificationModel calification) {
        super(EVENT_NAME);
        this.calification = calification;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", calification.getId().value(),
                "docente", calification.getDocente().value(),
                "asignatura", calification.getAsignatura().value(),
                "carrera", calification.getCarrera().value(),
                "universidad", calification.getUniversidad().value(),
                "periodo", calification.getPeriodo().value(),
                "actividad_evaluada", calification.getActividadEvaluada().value(),
                "porcentaje", calification.getPorcentaje().toString(),
                "student_id", calification.getIdEstudiante().value(),
                "nota", calification.getNota().toString()
        );
    }
}
