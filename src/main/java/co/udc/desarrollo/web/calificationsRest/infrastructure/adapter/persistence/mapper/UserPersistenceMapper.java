package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.mapper;

import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserRole;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserPassword;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.user.UserPersistenceDto;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserPersistenceMapper {

    public UserPersistenceDto fromModelToDto(final UserModel user) {
        return new UserPersistenceDto(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getPassword().value(),
                user.getRole().name(),
                user.getStatus().name(),
                null,
                null);
    }

    public UserEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new UserEntity(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("role"),
                resultSet.getString("status"),
                resultSet.getString("created_at"),
                resultSet.getString("updated_at"));
    }

    public UserModel fromEntityToModel(final UserEntity entity) {
        return new UserModel(
                new UserId(entity.id()),
                new UserName(entity.name()),
                new UserEmail(entity.email()),
                UserPassword.fromHash(entity.password()),
                UserRole.fromString(entity.role()),
                UserStatus.fromString(entity.status()));
    }

    public UserModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<UserModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<UserModel> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(fromResultSetToModel(resultSet));
        }
        return users;
    }

}
