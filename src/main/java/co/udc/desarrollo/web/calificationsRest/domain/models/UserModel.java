package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserRole;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserPassword;
import lombok.Value;

@Value
public class UserModel {

    UserId id;
    UserName name;
    UserEmail email;
    UserPassword password;
    UserRole role;
    UserStatus status;

    public static UserModel create(
            final UserId id,
            final UserName name,
            final UserEmail email,
            final UserPassword password,
            final UserRole role) {
        return new UserModel(id, name, email, password, role, UserStatus.PENDING);
    }

    public UserModel activate() {
        return new UserModel(id, name, email, password, role, UserStatus.ACTIVE);
    }

    public UserModel deactivate() {
        return new UserModel(id, name, email, password, role, UserStatus.INACTIVE);
    }

}
