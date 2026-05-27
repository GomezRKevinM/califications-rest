package co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.mapper;

import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentLastName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentName;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.dto.student.StudentPersistenceDto;
import co.udc.desarrollo.web.calificationsRest.infrastructure.adapter.persistence.entity.StudentEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public final class StudentPersistenceMapper {

    public StudentPersistenceDto fromModelToDto(final StudentModel student){
        return new StudentPersistenceDto(
                student.getId().value(),
                student.getName().value(),
                student.getLastName().value(),
                student.getCreated_at().toString(),
                student.getUpdate_at().toString()
        );
    }

    public StudentEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException{
        return new StudentEntity(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("lastName"),
                resultSet.getString("created_at"),
                resultSet.getString("updated_at")
        );
    }

    public StudentModel fromEntityToModel(final StudentEntity student){
        return new StudentModel(
                new StudentId(student.id()),
                new StudentName(student.name()),
            new StudentLastName(student.lastName()),
            student.created_at(),
            student.updated_at()
        );
    }

    public StudentModel fromResultSetToModel(final ResultSet resultSet) throws SQLException{
        return new StudentModel(
                new StudentId(resultSet.getString("id")),
                new StudentName(resultSet.getString("name")),
                new StudentLastName(resultSet.getString("lastName")),
                resultSet.getString("created_at"),
                resultSet.getString("updated_at")
        );
    }

    public List<StudentModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
        final List<StudentModel> students = new ArrayList<>();
        while (resultSet.next()){
            students.add(fromResultSetToModel(resultSet));
        }
        return students;
    }
}
