package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.CreateCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.DeleteCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.UpdateCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetCalificationByIdQuery;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.calification.DeleteCalificationResult;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.CalificationResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.CreateCalificationRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.DeleteCalificationResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.UpdateCalificationRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CalificationRestMapper {

    public CreateCalificationCommand toCreateCommand(final CreateCalificationRequest r){
        return new CreateCalificationCommand(
                r.docente(),
                r.asignatura(),
                r.carrera(),
                r.universidad(),
                r.periodo(),
                r.actividad_evaluada(),
                Double.parseDouble(r.porcentaje()),
                r.student_id(),
                Double.parseDouble(r.nota())
        );
    }

    public UpdateCalificationCommand toUpdateCommand(final String id, final UpdateCalificationRequest r){
        return new UpdateCalificationCommand(id,
                r.docente(),
                r.asignatura(),
                r.carrera(),
                r.universidad(),
                r.periodo(),
                r.actividad_evaluada(),
                Double.parseDouble(r.porcentaje()),
                Double.parseDouble(r.nota())
        );
    }

    public DeleteCalificationCommand toDeleteCommand(final String id){
        return new DeleteCalificationCommand(id);
    }

    public GetCalificationByIdQuery toGetByIdQuery(final String id){
        return new GetCalificationByIdQuery(id);
    }

    public DeleteCalificationResponse toDeleteResponse(final DeleteCalificationResult result){
        return new DeleteCalificationResponse(result.deleted(), result.code(), result.message());
    }

    public CalificationResponse toResponse(final CalificationModel calification){
        return new CalificationResponse(
                calification.getId().toString(),
                calification.getFecha().toString(),
                calification.getDocente().toString(),
                calification.getAsignatura().toString(),
                calification.getCarrera().toString(),
                calification.getUniversidad().toString(),
                calification.getPeriodo().toString(),
                calification.getActividadEvaluada().toString(),
                calification.getPorcentaje().toString(),
                calification.getIdEstudiante().toString(),
                calification.getNota().toString()
        );
    }

}
