package co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateStudentCommand(

    @NotBlank(message = "name no puede estar vacio")
        @Size(min = 3, message = "name debería tener al menos 3 caracteres") String name,

    @NotBlank(message = "last name no puede estar vacio")
        @Size(min = 3, message = "last name debería tener al menos 3 caracteres") String lastName )
{
}
