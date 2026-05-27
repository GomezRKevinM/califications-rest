package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.entity;

public record StudentEntity(
        String id,
        String name,
        String lastName,
        String created_at,
        String updated_at
) {
}
