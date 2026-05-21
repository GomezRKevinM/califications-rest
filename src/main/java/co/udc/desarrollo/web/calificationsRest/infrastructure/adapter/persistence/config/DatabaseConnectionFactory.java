package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.config;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public final class DatabaseConnectionFactory {

    public  Connection createConnection(final DatabaseConfig config) {
        try {
            return DriverManager.getConnection(
                    config.buildJdbcUrl(), config.username(), config.password());
        } catch (final SQLException exception) {
            // throw PersistenceException.becauseConnectionFailed(exception);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
