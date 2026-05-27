package co.udc.desarrollo.web.calificationsRest.application.service.student;

import co.udc.desarrollo.web.calificationsRest.application.port.in.student.CreateStudentUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.GetStudentByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.SaveStudentPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.command.student.CreateStudentCommand;
import co.udc.desarrollo.web.calificationsRest.application.service.mapper.StudentApplicationMapper;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Set;

@Log
@Service
@RequiredArgsConstructor
public class CreateStudentService implements CreateStudentUseCase {
    private final SaveStudentPort saveStudentPort;
    private final GetStudentByIdPort getStudentByIdPort;
    private final Validator validator;

    @Override
    public StudentModel execute(CreateStudentCommand command) {
        validateCommand(command);

        final StudentModel studentToSave = StudentApplicationMapper.fromCreateCommandToModel(command);
        final StudentModel saveStudent = saveStudentPort.save(studentToSave);

        return saveStudent;
    }

    private void validateCommand(final CreateStudentCommand command) {
        final Set<ConstraintViolation<CreateStudentCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
