package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student;

import jakarta.validation.constraints.NotBlank;

public record DeleteStudentCommand(
        @NotBlank(message = "id no puede estar vacio") String id
) {
}
