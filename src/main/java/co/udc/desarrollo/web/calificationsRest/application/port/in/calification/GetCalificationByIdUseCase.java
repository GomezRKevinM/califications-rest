package co.udc.desarrollo.web.calificationsRest.application.port.in.calification;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetCalificationByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetCalificationByIdUseCase {
    CalificationModel execute(@NotNull @Valid GetCalificationByIdQuery query);
}
