package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.mapper;

import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.*;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.calification.CalificationPersistenceDto;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.entity.CalificationEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public final class CalificationPersistenceMapper {

    public CalificationPersistenceDto fromModelToDto(final CalificationModel calification) {
        return new CalificationPersistenceDto(
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

    public CalificationEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
        return new CalificationEntity(
                resultSet.getString("id"),
                resultSet.getString("fecha"),
                resultSet.getString("docente"),
                resultSet.getString("asignatura"),
                resultSet.getString("carrera"),
                resultSet.getString("universidad"),
                resultSet.getString("periodo"),
                resultSet.getString("actividad_evaluada"),
                resultSet.getString("porcentaje"),
                resultSet.getString("student_id"),
                resultSet.getString("nota")
        );
    }

    public CalificationModel fromEntityToModel(final CalificationEntity entity) {
        return new CalificationModel(
                new CalificationId(entity.id()),
                new Timestamp(Long.getLong(entity.fecha())),
                new CalificationDocente(entity.docente()),
                new CalificationAsignatura(entity.asignatura()),
                new CalificationCarrera(entity.carrera()),
                new CalificationUniversidad(entity.universidad()),
                new CalificationPeriodo(entity.periodo()),
                new CalificationActividadEvaluada(entity.actividad_evaluada()),
                new CalificationPorcentaje(Double.parseDouble(entity.porcentaje())),
                new StudentId(entity.student_id()),
                new CalificationNota(Double.parseDouble(entity.nota()))
        );
    }

    public CalificationModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
        return new CalificationModel(
                new CalificationId(resultSet.getString("id")),
                new Timestamp(resultSet.getTimestamp("fecha").getTime()),
                new CalificationDocente(resultSet.getString("docente")),
                new CalificationAsignatura(resultSet.getString("asignatura")),
                new CalificationCarrera(resultSet.getString("carrera")),
                new CalificationUniversidad(resultSet.getString("universidad")),
                new CalificationPeriodo(resultSet.getString("periodo")),
                new CalificationActividadEvaluada(resultSet.getString("actividad_evaluada")),
                new CalificationPorcentaje(resultSet.getDouble("porcentaje")),
                new StudentId(resultSet.getString("student_id")),
                new CalificationNota(resultSet.getDouble("nota"))
        );
    }

    public List<CalificationModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<CalificationModel> califications = new ArrayList<>();
        while(resultSet.next()){
            califications.add(fromResultSetToModel(resultSet));
        }
        return califications;
    }

}
