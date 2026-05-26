package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.mapper;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.CreateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.DeleteStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.UpdateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetStudentByIdQuery;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user.DeleteStudentResult;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.CreateStudentRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.DeleteStudentResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.UpdateStudentRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.StudentResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentRestMapper {

    public CreateStudentCommand toCreateCommand(final CreateStudentRequest r) {
        return new CreateStudentCommand(r.name(), r.lastName());
    }

    public UpdateStudentCommand toUpdateCommand(final String id, final UpdateStudentRequest r) {
        return new UpdateStudentCommand(id, r.name(), r.lastName());
    }

    public DeleteStudentCommand toDeleteCommand(final String id) {
        return new DeleteStudentCommand(id);
    }

    public GetStudentByIdQuery toGetByIdQuery(final String id) {
        return new GetStudentByIdQuery(id);
    }


    public DeleteStudentResponse toDeleteResponse(final DeleteStudentResult result) {
        return new DeleteStudentResponse(result.deleted(), result.code(), result.message());
    }
    
    public StudentResponse toResponse(final StudentModel Student) {
        return new StudentResponse(
                Student.getId().toString(),
                Student.getName().toString(),
                Student.getLastName().toString(),
                Student.getCreated_at().toString(),
                Student.getUpdate_at().toString());
    }

}
