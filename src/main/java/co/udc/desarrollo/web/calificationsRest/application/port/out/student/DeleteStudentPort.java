package co.udc.desarrollo.web.calificationsRest.application.port.out.student;

import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.DeleteStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user.DeleteStudentResult;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;

public interface DeleteStudentPort {
    DeleteStudentResult delete(DeleteStudentCommand command);
}
