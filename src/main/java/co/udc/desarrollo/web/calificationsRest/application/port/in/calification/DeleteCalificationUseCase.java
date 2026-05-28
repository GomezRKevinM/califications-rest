package co.udc.desarrollo.web.calificationsRest.application.port.in.calification;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.DeleteCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.calification.DeleteCalificationResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteCalificationUseCase {
    DeleteCalificationResult execute(@NotNull @Valid DeleteCalificationCommand command);
}
