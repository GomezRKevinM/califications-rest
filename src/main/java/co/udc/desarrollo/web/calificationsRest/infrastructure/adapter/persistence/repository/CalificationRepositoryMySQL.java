package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.repository;

import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.DeleteCalificationPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.GetCalificationByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.SaveCalificationPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.GetAllCalificationPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.UpdateCalificationPort;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.calification.CalificationNotFoundException;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.CalificationId;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.calification.CalificationPersistenceDto;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.student.StudentPersistenceDto;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.exception.CalificationPersistenceException;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.exception.StudentPersistenceException;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.mapper.CalificationPersistenceMapper;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.mapper.StudentPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log
@Repository
@RequiredArgsConstructor
public class CalificationRepositoryMySQL implements SaveCalificationPort,
        UpdateCalificationPort,
        DeleteCalificationPort,
        GetCalificationByIdPort,
        GetAllCalificationPort
{

    private static final String SQL_INSERT = """
            INSERT INTO calificacion
                (id, fecha, docente, asignatura, carrera, universidad, periodo, actividad_evaluada, porcentaje, Calification_id, nota )
                VALUES (?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String SQL_UPDATE = """
            UPDATE calificacion SET docente = ?, asignatura = ?, carrera = ?, universidad = ?, periodo = ?, actividad_evaluada = ?, porcentaje = ?, Calification_id = ?, nota = ? 
            WHERE id = ?
            """;

    private static final String SQL_SELECT_BY_ID = """
            SELECT id, fecha, docente, asignatura, carrera, universidad, periodo, actividad_evaluada, porcentaje, Calification_id, nota 
            FROM calificacion 
            WHERE id = ?
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT id, fecha, docente, asignatura, carrera, universidad, periodo, actividad_evaluada, porcentaje, Calification_id, nota 
            FROM calificacion
            ORDER BY fecha DESC
            """;

    private static final String SQL_DELETE = """
            DELETE FROM calificacion
            WHERE id = ?
            """;

    private final Connection connection;


    @Override
    public void delete(CalificationId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, id.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw CalificationPersistenceException.becauseDeleteFailed(id.value(), exception);
        }
    }

    @Override
    public Optional<CalificationModel> getById(CalificationId calificationId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, calificationId.value());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(CalificationPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw CalificationPersistenceException.becauseFindByIdFailed(calificationId.value(), exception);
        }
    }

    @Override
    public CalificationModel save(CalificationModel calification) {
        final CalificationPersistenceDto dto = CalificationPersistenceMapper.fromModelToDto(calification);
        executeSave(dto);
        return findByIdOrFail(calification.getId());
    }

    @Override
    public List<CalificationModel> getAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return CalificationPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw CalificationPersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public CalificationModel update(CalificationModel calification) {
        final CalificationPersistenceDto dto = CalificationPersistenceMapper.fromModelToDto(calification);
        executeUpdate(dto);
        return findByIdOrFail(calification.getId());
    }

    private void executeSave(final CalificationPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.docente());
            statement.setString(3, dto.asignatura());
            statement.setString(4, dto.carrera());
            statement.setString(5, dto.universidad());
            statement.setString(6, dto.periodo());
            statement.setString(7, dto.actividad_evaluada());
            statement.setString(8, dto.porcentaje());
            statement.setString(9, dto.student_id());
            statement.setString(10, dto.nota());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw CalificationPersistenceException.becauseSaveFailed(dto.id(), exception);
        }
    }

    private void executeUpdate(final CalificationPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.docente());
            statement.setString(2, dto.asignatura());
            statement.setString(3, dto.carrera());
            statement.setString(4, dto.universidad());
            statement.setString(5, dto.periodo());
            statement.setString(6, dto.actividad_evaluada());
            statement.setString(7, dto.porcentaje());
            statement.setString(8, dto.id());
            statement.setString(9, dto.nota());
            statement.setString(10, dto.id());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw CalificationPersistenceException.becauseUpdateFailed(dto.id(), exception);
        }
    }

    private CalificationModel findByIdOrFail(final CalificationId calificationId) {
        return getById(calificationId)
                .orElseThrow(() -> CalificationNotFoundException.NOT_FOUND(calificationId.value()));
    }
}
