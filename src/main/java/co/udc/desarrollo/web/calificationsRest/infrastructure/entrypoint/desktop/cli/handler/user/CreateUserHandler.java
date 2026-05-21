package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserAlreadyExistsException;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.controller.UserController;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateUserHandler implements OperationHandler {
    private final UserController userController;
    private final ConsoleIO console;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final String id       = console.readRequired("ID                              : ");
        final String name     = console.readRequired("Name                            : ");
        final String email    = console.readRequired("Email                           : ");
        final String password = console.readRequired("Password                        : ");
        final String role     = console.readRequired("Role (ADMIN / MEMBER / REVIEWER): ");

        try {
            final UserResponse created =
                    userController.createUser(new CreateUserRequest(id, name, email, password, role));
            console.println("\n  Usuario creado correctamente.");
            printer.print(created);
        } catch (final UserAlreadyExistsException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }

}
