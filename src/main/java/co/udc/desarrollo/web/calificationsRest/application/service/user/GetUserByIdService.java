package co.udc.desarrollo.web.calificationsRest.application.service.user;

import co.udc.desarrollo.web.calificationsRest.application.port.in.user.GetUserByIdUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.user.GetUserByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetUserByIdQuery;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.UserApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.user.UserNotFoundException;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class GetUserByIdService implements GetUserByIdUseCase {

    private final GetUserByIdPort getUserByIdPort;
    private final Validator validator;

    @Override
    public UserModel execute(final GetUserByIdQuery query) {
        validateQuery(query);

        final UserId userId = UserApplicationMapper.fromGetUserByIdQueryToUserId(query);
        return getUserByIdPort
                .getById(userId)
                .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
    }

    private void validateQuery(final GetUserByIdQuery query) {
        final Set<ConstraintViolation<GetUserByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
