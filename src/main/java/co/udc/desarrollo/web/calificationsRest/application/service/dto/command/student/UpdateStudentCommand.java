package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public record UpdateStudentCommand(
        @NotBlank(message = "id no puede estar vacio") String id,

        @NotBlank(message = "nombre no puede estar vacio")
        @Size(min = 3, max = 50, message = "el nombre debe tener minimo 3 cracteres y maximo 50 caracteres") String nombre,

        @NotBlank(message = "apellido no puede estar vacio")
        @Size(min = 3, max = 50, message = "el apellido debe tener minimo 3 cracteres y maximo 50 caracteres")String apellido,

        @NotBlank(message = "updated_at no puede estar vacio")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp updatedAt
) {
}
