package co.udc.desarrollo.web.calificationsRest.application.service.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.CreateCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.DeleteCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.calification.UpdateCalificationCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetCalificationByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.*;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public final class CalificationApplicationMapper {

    public CalificationModel fromCreateCommandToModel(final CreateCalificationCommand command) {
        return new CalificationModel(
                new CalificationId(UUID.randomUUID().toString()),
                new CalificationDocente(command.docente()),
                new CalificationAsignatura(command.asignatura()),
                new CalificationCarrera(command.carrera()),
                new CalificationUniversidad(command.universidad()),
                new CalificationPeriodo(command.periodo()),
                new CalificationActividadEvaluada(command.actividadEvaluada()),
                new CalificationPorcentaje(command.porcentaje()),
                new StudentId(command.studentId()),
                new CalificationNota(command.nota())
        );
    }

    public CalificationModel fromUpdateCommandToModel(final UpdateCalificationCommand command) {
        return new CalificationModel(
                new CalificationId(command.id()),
                new CalificationDocente(command.docente()),
                new CalificationAsignatura(command.asignatura()),
                new CalificationCarrera(command.carrera()),
                new CalificationUniversidad(command.universidad()),
                new CalificationPeriodo(command.periodo()),
                new CalificationActividadEvaluada(command.actividadEvaluada()),
                new CalificationPorcentaje(command.porcentaje()),
                new StudentId(command.studentId()),
                new CalificationNota(command.nota())
        );
    }

    public CalificationId fromGetCalificationByIdQueryToCalificationId(final GetCalificationByIdQuery query){
        return new CalificationId(query.id());
    }

    public CalificationId fromDeleteCommandoToCalificationId(final DeleteCalificationCommand command){
        return new CalificationId(command.id());
    }
}
