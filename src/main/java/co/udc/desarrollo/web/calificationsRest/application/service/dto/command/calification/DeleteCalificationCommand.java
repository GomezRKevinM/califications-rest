package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification;

import jakarta.validation.constraints.NotBlank;

public record DeleteCalificationCommand(
        @NotBlank(message = "el id no puede estar vacío") String id
) {
}
