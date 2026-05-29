package co.udc.desarrollo.web.calificationsRest.application.service.calification;

import co.udc.desarrollo.web.calificationsRest.application.port.in.calification.CreateCalificationUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.SaveCalificationPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.CreateCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.CalificationApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
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
public class CreateCalificationService implements CreateCalificationUseCase {

    private final SaveCalificationPort savePort;
    private final Validator validator;

    @Override
    public CalificationModel execute(CreateCalificationCommand command) {
        validateCommand(command);

        final CalificationModel toSave = CalificationApplicationMapper.fromCreateCommandToModel(command);

        return savePort.save(toSave);
    }

    private void validateCommand(final CreateCalificationCommand command) {
        final Set<ConstraintViolation<CreateCalificationCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
