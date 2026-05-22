package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserCommand(

        @NotBlank(message = "name no puede estar vacio")
            @Size(min = 3, message = "name debería tener al menos 3 caracteres") String name,

        @NotBlank(message = "email no puede estar vacio")
            @Email(message = "email debe ser una dirección de correo valido") String email,

        @NotBlank(message = "password no puede estar vacio")
            @Size(min = 8, message = "password debe tener al menos 8 caracteres") String password,

        @NotBlank(message = "role no puede estar vacio") String role)
{

}
