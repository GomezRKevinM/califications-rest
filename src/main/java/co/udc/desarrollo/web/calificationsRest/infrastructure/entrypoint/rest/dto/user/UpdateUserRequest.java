package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank @Size(min = 3, max = 100) String name,
        @NotBlank @Email String email,
        @Size(min = 8, max = 100) String password,
        @NotBlank String role,
        @NotBlank String status
) {}
