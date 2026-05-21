package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.user;

public record UserPersistenceDto(
    String id,
    String name,
    String email,
    String password,
    String role,
    String status,
    String createdAt,
    String updatedAt )
{
}
