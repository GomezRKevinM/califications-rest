package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.controllers;

import co.udc.desarrollo.web.calificationsRest.application.port.in.auth.LoginUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.*;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.CreateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.DeleteUserResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.LoginRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.UpdateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.UserResponse;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.mapper.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final LoginUseCase loginUseCase;

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll() {
        var users = getAllUsersUseCase.execute();
        return ResponseEntity.ok(users.stream().map(UserRestMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        var query = UserRestMapper.toGetByIdQuery(id);
        var user = getUserByIdUseCase.execute(query);
        return ResponseEntity.ok(UserRestMapper.toResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserRequest request,
                                               UriComponentsBuilder uriBuilder) {
        var cmd = UserRestMapper.toCreateCommand(request);
        var created = createUserUseCase.execute(cmd);
        URI location = uriBuilder.path("/api/users/{id}")
                .buildAndExpand(created.getId().value())
                .toUri();
        return ResponseEntity.created(location).body(UserRestMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable String id,
                                               @RequestBody @Valid UpdateUserRequest request) {
        var cmd = UserRestMapper.toUpdateCommand(id, request);
        var updated = updateUserUseCase.execute(cmd);
        return ResponseEntity.ok(UserRestMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserResponse> delete(@PathVariable String id) {
        var cmd = UserRestMapper.toDeleteCommand(id);
        var result = deleteUserUseCase.execute(cmd);
        if (result.deleted()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(UserRestMapper.toDeleteResponse(result));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest request) {
        var cmd = UserRestMapper.toLoginCommand(request);
        var user = loginUseCase.execute(cmd);
        return ResponseEntity.ok(UserRestMapper.toResponse(user));
    }
}
