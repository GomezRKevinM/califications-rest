package co.udc.desarrollo.web.calificationsRest.application.service.calification;

import co.udc.desarrollo.web.calificationsRest.application.port.in.calification.GetCalificationByIdUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.GetCalificationByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetCalificationByIdQuery;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.CalificationApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.CalificationNotFoundException;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.CalificationId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Set;

@Log
@Service
@RequiredArgsConstructor
public class GetCalificationByIdService implements GetCalificationByIdUseCase {

    private final GetCalificationByIdPort getCalificationByIdPort;
    private final Validator validator;

    @Override
    public CalificationModel execute(GetCalificationByIdQuery query) {
        validateQuery(query);

        final CalificationId id = CalificationApplicationMapper.fromGetCalificationByIdQueryToCalificationId(query);
        return getCalificationByIdPort
                .getById(id)
                .orElseThrow(() -> CalificationNotFoundException.NOT_FOUND(id.toString()));
    }

    private void validateQuery(final GetCalificationByIdQuery query){
        final Set<ConstraintViolation<GetCalificationByIdQuery>> violations = validator.validate(query);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
