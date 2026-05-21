package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.exception;

public class PersistenceException extends RuntimeException {

    private static final String MESSAGE_SAVE = "Falló al guardar usuario con ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Falló al actualizar a usuario con ID: '%s'.";
    private static final String MESSAGE_FIND = "Falló al buscar usuario con ID: '%s'.";
    private static final String MESSAGE_EMAIL = "Falló al buscar usuario con email: '%s'.";
    private static final String MESSAGE_ALL = "Falló al obtener todos los usuarios.";
    private static final String MESSAGE_DELETE = "Falló al eliminar usuario con ID: '%s'.";
    private static final String MESSAGE_CONNECTION = "No se pudo establecer conexión con la base de datos.";

    private PersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_SAVE, userId), cause);
    }

    public static PersistenceException becauseUpdateFailed(
            final String userId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
    }

    public static PersistenceException becauseFindByIdFailed(
            final String userId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_FIND, userId), cause);
    }

    public static PersistenceException becauseFindByEmailFailed(
            final String email, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
    }

    public static PersistenceException becauseFindAllFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_ALL, cause);
    }

    public static PersistenceException becauseDeleteFailed(
            final String userId, final Throwable cause) {
        return new PersistenceException(String.format(MESSAGE_DELETE, userId), cause);
    }

    public static PersistenceException becauseConnectionFailed(final Throwable cause) {
        return new PersistenceException(MESSAGE_CONNECTION, cause);
    }

}
