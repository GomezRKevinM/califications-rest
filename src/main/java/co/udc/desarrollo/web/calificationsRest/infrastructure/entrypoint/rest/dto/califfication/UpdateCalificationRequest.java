package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication;

public record UpdateCalificationRequest(
        String docente,
        String asignatura,
        String carrera,
        String universidad,
        String periodo,
        String actividad_evaluada,
        String porcentaje,
        String nota
) {
}
