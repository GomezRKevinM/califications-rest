package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserCommand(

        @NotBlank(message = "id no debe estar vacío") String id,

        @NotBlank(message = "name no debe estar vacío")
            @Size(min = 3, message = "name debe tener al menos 3 caracteres") String name,

        @NotBlank(message = "email no debe estar vacio")
            @Email(message = "email debe tener una dirección de correo valida") String email,

        String password,

        @NotBlank(message = "role no debe estar vacío") String role,

        @NotBlank(message = "status no debe estar vacío") String status )
{
}
