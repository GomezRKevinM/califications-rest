package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication;

import jakarta.validation.constraints.NotBlank;

public record CreateCalificationRequest(
        @NotBlank String docente,
        @NotBlank String asignatura,
        @NotBlank String carrera,
        @NotBlank String universidad,
        @NotBlank String periodo,
        @NotBlank String actividad_evaluada,
        @NotBlank String porcentaje,
        @NotBlank String student_id,
        @NotBlank String nota
) {
}
