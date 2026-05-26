package co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user;

public record DeleteStudentResult(
        boolean deleted,
        String code,
        String message
) {

    public static DeleteStudentResult deleted(final String id) {
        return new DeleteStudentResult(
                true,
                "STUDENT_DELETED",
                "El estudiante con id '" + id + "' fue eliminado correctamente.");
    }

    public static DeleteStudentResult notFound(final String id) {
        return new DeleteStudentResult(
                false,
                "STUDENT_NOT_FOUND",
                "El estudiante con id '" + id + "' no existe o ya fue eliminado.");
    }
}
