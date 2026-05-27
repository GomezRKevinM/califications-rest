package co.udc.desarrollo.web.calificationsRest.application.service.student;

import co.udc.desarrollo.web.calificationsRest.application.port.in.student.GetAllStudentsUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.student.GetStudentByIdUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.GetStudentByIdPort;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetStudentByIdQuery;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.query.GetStudentByIdQuery;
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
public class GetStudentById implements GetStudentByIdUseCase {

    private final GetStudentByIdPort getStudentByIdPort;
    private final Validator validator;
    
    @Override
    public StudentModel execute(GetStudentByIdQuery query) {
        validateQuery(query);

        final StudentId studentId = StudentApplicationMapper.fromGetStudentByIdQueryToStudentId(query);
        return getStudentByIdPort.getById(studentId).orElseThrow(() -> StudentNotFoundException.becauseIdWasNotFound(studentId.toString())) ;
    }

    private void validateQuery(final GetStudentByIdQuery query) {
        final Set<ConstraintViolation<GetStudentByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
