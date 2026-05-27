package co.udc.desarrollo.web.calificationsRest.application.service.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.DeleteStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.UpdateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.CreateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetStudentByIdQuery;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentLastName;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public final class StudentApplicationMapper {

    public StudentModel fromCreateCommandToModel(final CreateStudentCommand command) {
        return StudentModel.create(
                new StudentId(UUID.randomUUID().toString()),
                new StudentName(command.name()),
                new StudentLastName(command.lastName())
        );
    }

    public StudentModel fromUpdateCommandToModel(
            final UpdateStudentCommand command) {

        return new StudentModel(
                new StudentId(command.id()),
                new StudentName(command.name()),
                new StudentLastName(command.lastName()));
    }

    public StudentId fromGetStudentByIdQueryToStudentId(final GetStudentByIdQuery query) {
        return new StudentId(query.id());
    }

    public StudentId fromDeleteCommandToStudentId(final DeleteStudentCommand command) {
        return new StudentId(command.id());
    }
    
}
