package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserNotFoundException;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.controller.UserController;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindUserByIdHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("ID del usuario: ");
        try {
            final UserResponse user = userController.findUserById(id);
            printer.print(user);
        } catch (final UserNotFoundException exception) {
            console.println("  No se encontró: " + exception.getMessage());
        }
    }

}
