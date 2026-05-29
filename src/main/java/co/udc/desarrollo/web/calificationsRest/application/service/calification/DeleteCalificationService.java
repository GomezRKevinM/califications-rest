package co.udc.desarrollo.web.calificationsRest.application.service.calification;

import co.udc.desarrollo.web.calificationsRest.application.port.in.calification.DeleteCalificationUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.DeleteCalificationPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.GetCalificationByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.DeleteCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.calification.DeleteCalificationResult;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.CalificationApplicationMapper;
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
public class DeleteCalificationService implements DeleteCalificationUseCase {
    private final DeleteCalificationPort deleteCalificationPort;
    private final GetCalificationByIdPort getByIdPort;
    private final Validator validator;

    @Override
    public DeleteCalificationResult execute(DeleteCalificationCommand command) {
        validateCommand(command);

        final CalificationId calificationId = CalificationApplicationMapper.fromDeleteCommandoToCalificationId(command);
        return getByIdPort.getById(calificationId)
                .map(calification -> deleteExistingCalification(calificationId))
                .orElseGet(() -> DeleteCalificationResult.notFound(calificationId.toString()));
    }

    private void validateCommand(final DeleteCalificationCommand command) {
        final Set<ConstraintViolation<DeleteCalificationCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private DeleteCalificationResult deleteExistingCalification(final CalificationId id) {
        deleteCalificationPort.delete(id);

        return DeleteCalificationResult.deleted(id.toString());
    }

}
