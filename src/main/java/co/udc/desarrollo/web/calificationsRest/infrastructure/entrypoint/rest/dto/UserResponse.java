package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto;

public record UserResponse(
    String id,
    String name,
    String email,
    String role,
    String status,
    String created_at,
    String updated_at
) {
}
