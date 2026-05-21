package co.udc.desarrollo.web.calificationsRest.infrastructure.config;

import co.udc.desarrollo.web.calificationsRest.application.port.in.auth.LoginUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.*;
import co.udc.desarrollo.web.calificationsRest.application.service.auth.LoginService;
import co.udc.desarrollo.web.calificationsRest.application.service.email.EmailNotificationService;
import co.udc.desarrollo.web.calificationsRest.application.service.user.*;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.email.SmtpConfig;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.config.DatabaseConfig;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import jakarta.validation.Validator;

import java.sql.Connection;

public final class DependencyContainer {

    private static final String DB_HOST = "db.host";
    private static final String DB_PORT = "db.port";
    private static final String DB_NAME = "db.name";
    private static final String DB_USER = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static final String SMTP_HOST = "smtp.host";
    private static final String SMTP_PORT = "smtp.port";
    private static final String SMTP_USER = "smtp.username";
    private static final String SMTP_PASSWORD = "smtp.password";
    private static final String SMTP_FROM = "smtp.from.address";
    private static final String SMTP_FROM_NAME = "smtp.from.name";

    private final UserController userController;

    public DependencyContainer() {
        final AppProperties properties = new AppProperties();

        final Connection connection = buildDatabaseConnection(properties);
        final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);

        final JavaMailEmailSenderAdapter emailSender =
                new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
        final EmailNotificationService emailNotification = new EmailNotificationService(emailSender);

        // Construir Validator para las validaciones en la capa de aplicación
        final Validator validator = ValidatorProvider.buildValidator();

        final CreateUserUseCase createUserUseCase =
                new CreateUserService(userRepository, userRepository, emailNotification, validator);
        final UpdateUserUseCase updateUserUseCase =
                new UpdateUserService(userRepository, userRepository, userRepository, emailNotification, validator);
        final DeleteUserUseCase deleteUserUseCase =
                new DeleteUserService(userRepository, userRepository, validator);
        final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdService(userRepository, validator);
        final GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersService(userRepository);
        final LoginUseCase loginUseCase = new LoginService(userRepository, validator);

        this.userController =
                new UserController(
                        createUserUseCase,
                        updateUserUseCase,
                        deleteUserUseCase,
                        getUserByIdUseCase,
                        getAllUsersUseCase,
                        loginUseCase);
    }

    public UserController userController() {
        return userController;
    }

    private static Connection buildDatabaseConnection(final AppProperties properties) {
        final DatabaseConfig config =
                new DatabaseConfig(
                        properties.get(DB_HOST),
                        properties.getInt(DB_PORT),
                        properties.get(DB_NAME),
                        properties.get(DB_USER),
                        properties.get(DB_PASSWORD));
        return DatabaseConnectionFactory.createConnection(config);
    }

    private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
        return new SmtpConfig(
                properties.get(SMTP_HOST),
                properties.getInt(SMTP_PORT),
                properties.get(SMTP_USER),
                properties.get(SMTP_PASSWORD),
                properties.get(SMTP_FROM),
                properties.get(SMTP_FROM_NAME));
    }

}
