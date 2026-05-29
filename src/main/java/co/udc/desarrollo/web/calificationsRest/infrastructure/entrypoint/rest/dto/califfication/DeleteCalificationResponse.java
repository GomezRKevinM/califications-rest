package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication;

public record DeleteCalificationResponse(
        boolean deleted,
        String code,
        String message
) {
}
