package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginCommand(

        @NotBlank(message = "email no debe estar vacío")
            @Email(message = "email debe tener una dirección de correo valida") String email,

        @NotBlank(message = "password no debe estar vacío")
            @Size(min = 8, message = "password debe tener al menos 8 caracteres") String password
) {
}
