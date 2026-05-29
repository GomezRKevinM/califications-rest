package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.controllers;

import co.udc.desarrollo.web.calificationsRest.application.port.in.calification.*;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.CalificationResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.CreateCalificationRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.DeleteCalificationResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.califfication.UpdateCalificationRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.mapper.CalificationRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/califications")
@RequiredArgsConstructor
public class CalificationRestController {

    private final CreateCalificationUseCase createCalificationUseCase;
    private final UpdateCalificationUseCase updateCalificationUseCase;
    private final DeleteCalificationUseCase deleteCalificationUseCase;
    private final GetCalificationByIdUseCase getCalificationByIdUseCase;
    private final GetAllCalificationUseCase getAllCalificationUseCase;

    @GetMapping
    public ResponseEntity<List<CalificationResponse>> listAll(){
        var califications = getAllCalificationUseCase.execute();
        return ResponseEntity.ok(califications.stream().map(CalificationRestMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificationResponse> getById(@PathVariable String id){
        var query = CalificationRestMapper.toGetByIdQuery(id);
        var calification = getCalificationByIdUseCase.execute(query);
        return ResponseEntity.ok(CalificationRestMapper.toResponse(calification));
    }

    @PostMapping
    public ResponseEntity<CalificationResponse> create(@RequestBody @Valid CreateCalificationRequest request, UriComponentsBuilder uriBuilder){
        var cmd = CalificationRestMapper.toCreateCommand(request);
        var created = createCalificationUseCase.execute(cmd);
        URI location = uriBuilder.path("/api/califications/{id}")
                .buildAndExpand(created.getId().value())
                .toUri();
        return ResponseEntity.created(location).body(CalificationRestMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificationResponse> update(@PathVariable String id, @RequestBody @Valid UpdateCalificationRequest request){
        var cmd = CalificationRestMapper.toUpdateCommand(id,request);
        var updated = updateCalificationUseCase.execute(cmd);
        return ResponseEntity.ok(CalificationRestMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCalificationResponse> delete(@PathVariable String id){
        var cmd = CalificationRestMapper.toDeleteCommand(id);
        var result = deleteCalificationUseCase.execute(cmd);
        if(result.deleted()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CalificationRestMapper.toDeleteResponse(result));
    }


}
