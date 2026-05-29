package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.entity;

public record CalificationEntity(
        String id,
        String fecha,
        String docente,
        String asignatura,
        String carrera,
        String universidad,
        String periodo,
        String actividad_evaluada,
        String porcentaje,
        String student_id,
        String nota
) {
}
