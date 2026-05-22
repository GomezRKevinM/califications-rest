package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.auth.LoginCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.CreateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.UpdateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetUserByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.CreateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.LoginRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.UpdateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRestMapper {

    public CreateUserCommand toCreateCommand(final CreateUserRequest r) {
        return new CreateUserCommand(r.id(), r.name(), r.email(), r.password(), r.role());
    }

    public UpdateUserCommand toUpdateCommand(final String id, final UpdateUserRequest r) {
        return new UpdateUserCommand(id, r.name(), r.email(), r.password(), r.role(), r.status());
    }

    public DeleteUserCommand toDeleteCommand(final String id) {
        return new DeleteUserCommand(id);
    }

    public GetUserByIdQuery toGetByIdQuery(final String id) {
        return new GetUserByIdQuery(id);
    }

    public LoginCommand toLoginCommand(final LoginRequest r) {
        return new LoginCommand(r.email(), r.password());
    }

    /**
     * Transforma un {@link UserModel} a un {@link UserResponse}.
     * Incluye todos los datos del usuario incluyendo createdAt y updatedAt.
     *
     * @param user modelo de usuario de dominio con datos de persistencia
     * @return DTO de respuesta con información completa del usuario
     */
    public UserResponse toResponse(final UserModel user) {
        return new UserResponse(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getRole().name(),
                user.getStatus().name(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

}
