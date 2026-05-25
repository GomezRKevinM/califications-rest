package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.user;

public record DeleteUserResponse(
        boolean deleted,
        String code,
        String message
) {
}
