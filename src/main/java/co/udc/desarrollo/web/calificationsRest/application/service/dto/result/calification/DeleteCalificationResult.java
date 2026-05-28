package co.udc.desarrollo.web.calificationsRest.application.service.dto.result.calification;

public record DeleteCalificationResult(
        boolean deleted,
        String code,
        String message
) {

    public static DeleteCalificationResult deleted(final String id){
        return new DeleteCalificationResult(
                true,
                "CALIFICATION_DELETED",
                "La calificación con id '" + id + "' fue eliminada correctamente."
        );
    }

    public static DeleteCalificationResult notFound(final String id){
        return new DeleteCalificationResult(
                false,
                "CALIFICATION_NOT_FOUND",
                "La calificación con id '" + id + "' no existe o ya fue eliminada"
        );
    }
}
