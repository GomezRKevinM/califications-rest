package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.user;

import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserNotFoundException;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.controller.UserController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteUserHandler implements OperationHandler {

    private final UserController userController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        final String id = console.readRequired("ID del usuaririo a eliminar: ");
        try {
            userController.deleteUser(id);
            console.println("  Usuario eliminado correctamente.");
        } catch (final UserNotFoundException exception) {
            console.println("  No se encontró: " + exception.getMessage());
        }
    }

}
