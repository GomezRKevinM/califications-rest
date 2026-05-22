package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.auth.LoginCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.CreateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.UpdateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetUserByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.CreateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.LoginRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.UpdateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.desktop.dto.UserResponse;

import java.util.List;

public class UserDesktopMapper {
    private UserDesktopMapper() {}

    public static CreateUserCommand toCreateCommand(final CreateUserRequest request) {
        return new CreateUserCommand(
                request.name(), request.email(), request.password(), request.role());
    }

    public static UpdateUserCommand toUpdateCommand(final UpdateUserRequest request) {
        return new UpdateUserCommand(
                request.id(),
                request.name(),
                request.email(),
                request.password(),
                request.role(),
                request.status());
    }

    public static DeleteUserCommand toDeleteCommand(final String id) {
        return new DeleteUserCommand(id);
    }

    public static GetUserByIdQuery toGetByIdQuery(final String id) {
        return new GetUserByIdQuery(id);
    }

    public static LoginCommand toLoginCommand(final LoginRequest request) {
        return new LoginCommand(request.email(), request.password());
    }

    public static UserResponse toResponse(final UserModel user) {
        return new UserResponse(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getRole().name(),
                user.getStatus().name());
    }

    public static List<UserResponse> toResponseList(final List<UserModel> users) {
        return users.stream().map(UserDesktopMapper::toResponse).toList();
    }

}
