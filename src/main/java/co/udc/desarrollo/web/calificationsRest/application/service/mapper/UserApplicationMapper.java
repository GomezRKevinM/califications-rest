package co.udc.desarrollo.web.calificationsRest.application.service.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.CreateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.DeleteUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.user.UpdateUserCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetUserByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserRole;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserPassword;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class UserApplicationMapper {

    public UserModel fromCreateCommandToModel(final CreateUserCommand command) {
        return UserModel.create(
                new UserId(UUID.randomUUID().toString()),
                new UserName(command.name()),
                new UserEmail(command.email()),
                UserPassword.fromPlainText(command.password()),
                UserRole.fromString(command.role()));
    }

    public UserModel fromUpdateCommandToModel(
            final UpdateUserCommand command, final UserPassword currentPassword) {

        final UserPassword passwordToUse = resolvePassword(command.password(), currentPassword);

        return new UserModel(
                new UserId(command.id()),
                new UserName(command.name()),
                new UserEmail(command.email()),
                passwordToUse,
                UserRole.fromString(command.role()),
                UserStatus.fromString(command.status()));
    }

    public UserId fromGetUserByIdQueryToUserId(final GetUserByIdQuery query) {
        return new UserId(query.id());
    }

    public UserId fromDeleteCommandToUserId(final DeleteUserCommand command) {
        return new UserId(command.id());
    }

    private UserPassword resolvePassword(
            final String newPlainPassword, final UserPassword currentPassword) {
        if (Objects.isNull(newPlainPassword) || newPlainPassword.isBlank()) {
            return currentPassword;
        }
        return UserPassword.fromPlainText(newPlainPassword);
    }

}
