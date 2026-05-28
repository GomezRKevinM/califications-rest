package co.udc.desarrollo.web.calificationsRest.application.port.in.calification;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.CreateCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateCalificationUseCase {
    CalificationModel execute(@NotNull @Valid CreateCalificationCommand command);
}
