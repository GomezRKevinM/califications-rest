package co.udc.desarrollo.web.calificationsRest.infrastructure.config;

public final class ConfigurationException extends RuntimeException {

    private static final String MESSAGE_LOAD = "Falló al cargar la configuración de la aplicación.";

    private ConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static ConfigurationException becauseLoadFailed(final Throwable cause) {
        return new ConfigurationException(MESSAGE_LOAD, cause);
    }

}
