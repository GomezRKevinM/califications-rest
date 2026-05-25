package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student;

public record DeleteStudentResponse(
        boolean deleted,
        String code,
        String message
) {
}
