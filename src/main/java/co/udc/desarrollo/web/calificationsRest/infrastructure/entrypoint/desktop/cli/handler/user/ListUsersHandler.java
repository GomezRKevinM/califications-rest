package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.user;

import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.controller.UserController;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListUsersHandler implements OperationHandler {

    private final UserController userController;
    private final UserResponsePrinter printer;

    @Override
    public void handle() {
        final List<UserResponse> users = userController.listAllUsers();
        printer.printList(users);
    }

}
