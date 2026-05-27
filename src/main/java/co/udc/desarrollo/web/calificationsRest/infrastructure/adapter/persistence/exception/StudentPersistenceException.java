package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.exception;

public class StudentPersistenceException extends RuntimeException {
    private static final String MESSAGE_SAVE = "Falló al guardar estudiante con ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Falló al actualizar a estudiante con ID: '%s'.";
    private static final String MESSAGE_FIND = "Falló al buscar estudiante con ID: '%s'.";
    private static final String MESSAGE_ALL = "Falló al obtener todos los estudiantes.";
    private static final String MESSAGE_DELETE = "Falló al eliminar estudiante con ID: '%s'.";
    private static final String MESSAGE_CONNECTION = "No se pudo establecer conexión con la base de datos.";

    private StudentPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static StudentPersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
        return new StudentPersistenceException(String.format(MESSAGE_SAVE, userId), cause);
    }

    public static StudentPersistenceException becauseUpdateFailed(
            final String userId, final Throwable cause) {
        return new StudentPersistenceException(String.format(MESSAGE_UPDATE, userId), cause);
    }

    public static StudentPersistenceException becauseFindByIdFailed(
            final String userId, final Throwable cause) {
        return new StudentPersistenceException(String.format(MESSAGE_FIND, userId), cause);
    }

    public static StudentPersistenceException becauseFindAllFailed(final Throwable cause) {
        return new StudentPersistenceException(MESSAGE_ALL, cause);
    }

    public static StudentPersistenceException becauseDeleteFailed(
            final String userId, final Throwable cause) {
        return new StudentPersistenceException(String.format(MESSAGE_DELETE, userId), cause);
    }

    public static StudentPersistenceException becauseConnectionFailed(final Throwable cause) {
        return new StudentPersistenceException(MESSAGE_CONNECTION, cause);
    }

}
