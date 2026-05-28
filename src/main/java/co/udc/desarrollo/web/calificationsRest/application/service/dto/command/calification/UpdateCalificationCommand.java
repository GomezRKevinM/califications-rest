package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCalificationCommand(
        @NotBlank(message = "id no puede estar vacio") String id,

        @NotBlank(message = "Docente no puede estar vacío") String docente,

        @NotBlank(message = "Asignatura no puede estar vacía") String asignatura,

        @NotBlank(message = "Carrera no puede estar vacía")
        @Size(min = 3, message = "Carrera debe tener al menos 3 caracteres") String carrera,

        @NotBlank(message = "Universidad no puede estar vacía")
        @Size(min = 3, message = "Universidad debe tener al menos 3 caracteres") String universidad,

        @NotBlank(message = "Periodo no puede estar vacío")
        @Size(min = 1, max = 3, message = "Periodo debe tener minimo 1 digito y maximo 3") String periodo,

        @NotBlank(message = "Actividad evaluada no puede estar vacía") String actividadEvaluada,

        @NotBlank(message = "Porcentaje no puede estar vacio")
        @Size(min = 0, max = 1, message = "porcentaje debe estar entre 0 y 1") double porcentaje,

        @NotBlank(message = "el id del estudiante no puede estar vacio")
        @Size( min = 15, message = "el tamaño del id debe ser de al menos 15 digitos") String studentId,

        @NotBlank(message = "Nota no debe estar vacia")
        @Size( min = 0, max = 5, message = "La nota debe estar entre 0 y 5") double nota
) {
}
