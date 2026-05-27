package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.controllers;


import co.udc.desarrollo.web.calificationsRest.application.port.in.student.*;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.CreateStudentRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.DeleteStudentResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.StudentResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.student.UpdateStudentRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.mapper.StudentRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;
    private final GetStudentByIdUseCase getStudentByIdUseCase;
    private final GetAllStudentsUseCase getAllStudentsUseCase;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> listAll(){
        var students = getAllStudentsUseCase.execute();
        return ResponseEntity.ok(students.stream().map(StudentRestMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable String id){
        var query = StudentRestMapper.toGetByIdQuery(id);
        var student = getStudentByIdUseCase.execute(query);
        return ResponseEntity.ok(StudentRestMapper.toResponse(student));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid CreateStudentRequest request, UriComponentsBuilder uriBuilder){

        var cmd = StudentRestMapper.toCreateCommand(request);
        var created = createStudentUseCase.execute(cmd);
        URI location = uriBuilder.path("/api/students/{id}")
                .buildAndExpand(created.getId().value())
                .toUri();
        return ResponseEntity.created(location).body(StudentRestMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable String id, @RequestBody @Valid UpdateStudentRequest request){
        var cmd = StudentRestMapper.toUpdateCommand(id,request);
        var updated = updateStudentUseCase.execute(cmd);
        return ResponseEntity.ok(StudentRestMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteStudentResponse> delete(@PathVariable String id){
        var cmd = StudentRestMapper.toDeleteCommand(id);
        var result = deleteStudentUseCase.execute(cmd);
        if(result.deleted()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(StudentRestMapper.toDeleteResponse(result));
    }
}
