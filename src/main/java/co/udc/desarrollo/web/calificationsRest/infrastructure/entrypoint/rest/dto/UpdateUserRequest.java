package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(min = 2, max = 100) String name,
        @Email String email,
        @Size(min = 6, max = 100) String password,
        String role,
        String status
) {}

