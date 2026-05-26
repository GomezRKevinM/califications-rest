package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student;

public record StudentResponse(
        String id,
        String name,
        String lastName,
        String created_at,
        String updated_at )
{
}
