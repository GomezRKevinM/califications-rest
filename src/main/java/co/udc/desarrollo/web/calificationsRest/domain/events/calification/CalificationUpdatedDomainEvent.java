package co.udc.desarrollo.web.calificationsRest.domain.events.calification;

import co.udc.desarrollo.web.calificationsRest.domain.events.DomainEvent;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;

import java.util.Map;

public final class CalificationUpdatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "calification.updated";
    private final CalificationModel calification;

    public CalificationUpdatedDomainEvent(final CalificationModel calification) {
        super(EVENT_NAME);
        this.calification = calification;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", calification.getId().toString(),
                "docente", calification.getDocente().toString(),
                "asignatura", calification.getAsignatura().toString(),
                "carrera", calification.getCarrera().toString(),
                "universidad", calification.getUniversidad().toString(),
                "periodo", calification.getPeriodo().toString(),
                "actividad_evaluada", calification.getActividadEvaluada().toString(),
                "porcentaje", calification.getPorcentaje().toString(),
                "student_id", calification.getIdEstudiante().toString(),
                "nota", calification.getNota().toString()
        );
    }
}
