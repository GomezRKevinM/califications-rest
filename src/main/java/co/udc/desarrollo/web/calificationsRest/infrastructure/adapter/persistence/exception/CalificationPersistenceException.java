package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.exception;

public class CalificationPersistenceException extends RuntimeException {
    private static final String MESSAGE_SAVE = "Falló al guardar calificación con ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Falló al actualizar a calificación con ID: '%s'.";
    private static final String MESSAGE_FIND = "Falló al buscar calificación con ID: '%s'.";
    private static final String MESSAGE_ALL = "Falló al obtener todos los calificacións.";
    private static final String MESSAGE_DELETE = "Falló al eliminar calificación con ID: '%s'.";
    private static final String MESSAGE_CONNECTION = "No se pudo establecer conexión con la base de datos.";

    private CalificationPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static CalificationPersistenceException becauseSaveFailed(final String calificationId, final Throwable cause) {
        return new CalificationPersistenceException(String.format(MESSAGE_SAVE, calificationId), cause);
    }

    public static CalificationPersistenceException becauseUpdateFailed(
            final String calificationId, final Throwable cause) {
        return new CalificationPersistenceException(String.format(MESSAGE_UPDATE, calificationId), cause);
    }

    public static CalificationPersistenceException becauseFindByIdFailed(
            final String calificationId, final Throwable cause) {
        return new CalificationPersistenceException(String.format(MESSAGE_FIND, calificationId), cause);
    }

    public static CalificationPersistenceException becauseFindAllFailed(final Throwable cause) {
        return new CalificationPersistenceException(MESSAGE_ALL, cause);
    }

    public static CalificationPersistenceException becauseDeleteFailed(
            final String calificationId, final Throwable cause) {
        return new CalificationPersistenceException(String.format(MESSAGE_DELETE, calificationId), cause);
    }

    public static CalificationPersistenceException becauseConnectionFailed(final Throwable cause) {
        return new CalificationPersistenceException(MESSAGE_CONNECTION, cause);
    }

}
