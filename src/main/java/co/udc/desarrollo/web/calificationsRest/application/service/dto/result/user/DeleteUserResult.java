package co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user;

public record DeleteUserResult(
        boolean deleted,
        String code,
        String message
) {

    public static DeleteUserResult deleted(final String id) {
        return new DeleteUserResult(
                true,
                "USER_DELETED",
                "El usuario con id '" + id + "' fue eliminado correctamente.");
    }

    public static DeleteUserResult notFound(final String id) {
        return new DeleteUserResult(
                false,
                "USER_NOT_FOUND",
                "El usuario con id '" + id + "' no existe o ya fue eliminado.");
    }
}
