package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.student;

public final record StudentPersistenceDto(
        String id,
        String name,
        String lastName,
        String created_at,
        String updated_at
) {
}
