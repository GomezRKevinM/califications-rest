package co.udc.desarrollo.web.calificationsRest.application.service.student;

import co.udc.desarrollo.web.calificationsRest.application.port.in.student.DeleteStudentUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.DeleteStudentPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.GetStudentByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.DeleteStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.student.DeleteStudentResult;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.StudentApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeleteStudentService implements DeleteStudentUseCase {

    private final DeleteStudentPort deletePort;
    private final GetStudentByIdPort getStudentPort;
    private final Validator validator;

    @Override
    public DeleteStudentResult execute(final DeleteStudentCommand command){
        validateCommand(command);

        final StudentId studentId = StudentApplicationMapper.fromDeleteCommandToStudentId(command);
        return getStudentPort.getById(studentId)
                .map(student -> deleteExistingStudent(studentId,student))
                .orElseGet(() -> DeleteStudentResult.notFound(studentId.value()));
    }

    private void validateCommand(final DeleteStudentCommand command) {
        final Set<ConstraintViolation<DeleteStudentCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private DeleteStudentResult deleteExistingStudent(final StudentId studentId, final StudentModel student) {
        deletePort.delete(studentId);

        return DeleteStudentResult.deleted(studentId.value());
    }
}
