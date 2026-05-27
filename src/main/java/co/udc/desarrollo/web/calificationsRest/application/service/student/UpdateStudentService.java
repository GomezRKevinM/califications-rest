package co.udc.desarrollo.web.calificationsRest.application.service.student;

import co.udc.desarrollo.web.calificationsRest.application.port.in.student.UpdateStudentUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.GetStudentByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.UpdateStudentPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.UpdateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.StudentApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.exceptions.student.StudentNotFoundException;
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
public class UpdateStudentService implements UpdateStudentUseCase {
    private final UpdateStudentPort updateStudentPort;
    private final GetStudentByIdPort getStudentByIdPort;
    private final Validator validator;
    
    @Override
    public StudentModel execute(UpdateStudentCommand command) {
        validateCommand(command);

        final StudentId studentId = new StudentId(command.id());
        final StudentModel current = findExistingStudentOrFail(studentId);

        return StudentApplicationMapper.fromUpdateCommandToModel(command);
    }

    private void validateCommand(final UpdateStudentCommand command) {
        final Set<ConstraintViolation<UpdateStudentCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private StudentModel findExistingStudentOrFail(final StudentId StudentId) {
        return getStudentByIdPort
                .getById(StudentId)
                .orElseThrow(() -> StudentNotFoundException.becauseIdWasNotFound(StudentId.value()));
    }
}
