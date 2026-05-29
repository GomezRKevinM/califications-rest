package co.udc.desarrollo.web.calificationsRest.application.service.calification;

import co.udc.desarrollo.web.calificationsRest.application.port.in.calification.GetAllCalificationUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.out.calification.GetAllCalificationPort;
import co.udc.desarrollo.web.calificationsRest.domain.models.CalificationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class GetAllCalificationService implements GetAllCalificationUseCase {

    private final GetAllCalificationPort getAllCalificationPort;

    @Override
    public List<CalificationModel> execute() {
        return getAllCalificationPort.getAll();
    }
}
