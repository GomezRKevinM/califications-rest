package co.udc.desarrollo.web.calificationsRest.application.service.student;

import co.udc.desarrollo.web.calificationsRest.application.port.in.student.GetAllStudentsUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.student.GetAllStudentPort;
import co.udc.desarrollo.web.calificationsRest.domain.models.StudentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllStudentService implements GetAllStudentsUseCase {

    private final GetAllStudentPort getAllStudentsPort;

    @Override
    public List<StudentModel> execute(){
        return getAllStudentsPort.getAll();
    }
}
