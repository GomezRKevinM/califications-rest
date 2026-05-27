package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.repository;

import co.udc.desarrollo.web.calificationsRest.application.port.out.student.*;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.student.StudentNotFoundException;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.student.StudentPersistenceDto;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.exception.StudentPersistenceException;
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
public class StudentRepositoryMySQL implements SaveStudentPort,
        UpdateStudentPort,
        DeleteStudentPort,
        GetStudentByIdPort,
        GetAllStudentPort {

    private static final String SQL_INSERT =
            "INSERT INTO students "
                    + "(id, name, lastName, created_at, updated_at) "
                    + "VALUES (?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE students SET name = ?, lastName = ?, updated_at = NOW() "
                    + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, lastName, created_at, updated_at "
                    + "FROM students "
                    + "WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, lastName, created_at, updated_at "
                    + "FROM students "
                    + "ORDER BY name ASC";

    private static final String SQL_DELETE =
            "DELETE FROM students "
                    + "WHERE id = ?";

    private final Connection connection;

    @Override
    public StudentModel save(StudentModel student) {
        final StudentPersistenceDto dto = StudentPersistenceMapper.fromModelToDto(student);
        executeSave(dto);
        return null;
    }

    @Override
    public void delete(StudentId studentId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, studentId.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw StudentPersistenceException.becauseDeleteFailed(studentId.value(), exception);
        }
    }

    @Override
    public List<StudentModel> getAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return StudentPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw StudentPersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public Optional<StudentModel> getById(StudentId studentId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, studentId.value());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(StudentPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw StudentPersistenceException.becauseFindByIdFailed(studentId.value(), exception);
        }
    }

    @Override
    public StudentModel update(StudentModel student) {
        final StudentPersistenceDto dto = StudentPersistenceMapper.fromModelToDto(student);
        executeUpdate(dto);
        return findByIdOrFail(student.getId());
    }

    private void executeSave(final StudentPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.name());
            statement.setString(3, dto.lastName());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw StudentPersistenceException.becauseSaveFailed(dto.id(), exception);
        }
    }

    private void executeUpdate(final StudentPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.name());
            statement.setString(2, dto.lastName());
            statement.setString(6, dto.id());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw StudentPersistenceException.becauseUpdateFailed(dto.id(), exception);
        }
    }

    private StudentModel findByIdOrFail(final StudentId studentId) {
        return getById(studentId)
                .orElseThrow(() -> StudentNotFoundException.becauseIdWasNotFound(studentId.value()));
    }
}
