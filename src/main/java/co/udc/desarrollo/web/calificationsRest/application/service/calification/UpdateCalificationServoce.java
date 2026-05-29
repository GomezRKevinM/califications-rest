package co.udc.desarrollo.web.calificationsRest.application.service.calification;

import co.udc.desarrollo.web.calificationsRest.application.port.in.calification.UpdateCalificationUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.GetCalificationByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.UpdateCalificationPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.DeleteCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.UpdateCalificationCommand;
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
public class UpdateCalificationServoce implements UpdateCalificationUseCase {
    private final UpdateCalificationPort updatePort;
    private final GetCalificationByIdPort getCalificationByIdPort;
    private final Validator validator;


    @Override
    public CalificationModel execute(UpdateCalificationCommand command) {
        validateCommand(command);

        final CalificationId calificationId = new CalificationId(command.id());
        final CalificationModel current = findExistingCalificationOrFail(calificationId);

        return CalificationApplicationMapper.fromUpdateCommandToModel(command);
    }

    private CalificationModel findExistingCalificationOrFail(final CalificationId calificationId){
        return getCalificationByIdPort
                .getById(calificationId)
                .orElseThrow(() -> CalificationNotFoundException.NOT_FOUND(calificationId.toString()));
    }

    private void validateCommand(final UpdateCalificationCommand command) {
        final Set<ConstraintViolation<UpdateCalificationCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
