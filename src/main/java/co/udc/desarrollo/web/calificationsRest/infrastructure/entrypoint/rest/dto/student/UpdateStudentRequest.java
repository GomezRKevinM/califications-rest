package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateStudentRequest(
        @NotBlank @Size(min = 3, max = 100) String name,
        @NotBlank @Size(min = 3, max = 100) String lastName
) {}
