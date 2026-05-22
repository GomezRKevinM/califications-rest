package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserRole;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserPassword;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserModel {

    UserId id;
    UserName name;
    UserEmail email;
    UserPassword password;
    UserRole role;
    UserStatus status;
    String createdAt;
    String updatedAt;

    public UserModel(UserId id, UserName name, UserEmail email, UserPassword password,
                     UserRole role, UserStatus status) {
        this(id, name, email, password, role, status, null, null);
    }

    public static UserModel create(
            final UserId id,
            final UserName name,
            final UserEmail email,
            final UserPassword password,
            final UserRole role) {
        return new UserModel(id, name, email, password, role, UserStatus.PENDING);
    }

    public UserModel activate() {
        return new UserModel(id, name, email, password, role, UserStatus.ACTIVE, createdAt, updatedAt);
    }

    public UserModel deactivate() {
        return new UserModel(id, name, email, password, role, UserStatus.INACTIVE, createdAt, updatedAt);
    }

}
