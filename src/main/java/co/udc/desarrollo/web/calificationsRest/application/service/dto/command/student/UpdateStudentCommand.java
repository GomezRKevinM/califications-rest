package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateStudentCommand(

        @NotBlank(message = "id no debe estar vacío") String id,

        @NotBlank(message = "name no debe estar vacío")
            @Size(min = 3, message = "name debe tener al menos 3 caracteres") String name,

        @NotBlank(message = "last name no debe estar vacío")
        @Size(min = 3, message = "last name debe tener al menos 3 caracteres") String lastName )
{
}
