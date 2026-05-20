package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserCommand(
        @NotBlank(message = "id no puede estar vacio") String id
) {
}
