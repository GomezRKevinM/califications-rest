package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.auth;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.auth.InvalidCredentialsException;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.controller.UserController;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.LoginRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class LoginHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final String email    = console.readRequired("Email   : ");
        final String password = console.readRequired("Password: ");
        try {
            final UserResponse user = userController.login(new LoginRequest(email, password));
            console.println("\n  Inicio de sesión exitoso!, Bienvenido:");
            printer.print(user);
        } catch (final InvalidCredentialsException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }

}
