package co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.controllers;

import co.udc.desarrollo.web.calificationsRest.application.port.in.auth.LoginUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.CreateUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.DeleteUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.GetAllUsersUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.GetUserByIdUseCase;
import co.udc.desarrollo.web.calificationsRest.application.port.in.user.UpdateUserUseCase;
import co.udc.desarrollo.web.calificationsRest.application.service.dto.result.user.DeleteUserResult;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserRole;
import co.udc.desarrollo.web.calificationsRest.domain.enums.user.UserStatus;
import co.udc.desarrollo.web.calificationsRest.domain.models.UserModel;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserEmail;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserId;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserName;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.user.UserPassword;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.CreateUserRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.LoginRequest;
import co.udc.desarrollo.web.calificationsRest.infrastructure.entrypoint.rest.dto.UpdateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestControllerTest {

    private final CreateUserUseCase createUserUseCase = mock(CreateUserUseCase.class);
    private final UpdateUserUseCase updateUserUseCase = mock(UpdateUserUseCase.class);
    private final DeleteUserUseCase deleteUserUseCase = mock(DeleteUserUseCase.class);
    private final GetUserByIdUseCase getUserByIdUseCase = mock(GetUserByIdUseCase.class);
    private final GetAllUsersUseCase getAllUsersUseCase = mock(GetAllUsersUseCase.class);
    private final LoginUseCase loginUseCase = mock(LoginUseCase.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserRestController(
                        createUserUseCase,
                        updateUserUseCase,
                        deleteUserUseCase,
                        getUserByIdUseCase,
                        getAllUsersUseCase,
                        loginUseCase))
                .build();
    }

    @Test
    void listAllUsesGetApiUsers() throws Exception {
        when(getAllUsersUseCase.execute()).thenReturn(List.of(user("user-1")));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("user-1"));
    }

    @Test
    void getByIdUsesGetApiUsersId() throws Exception {
        when(getUserByIdUseCase.execute(any())).thenReturn(user("user-1"));

        mockMvc.perform(get("/api/users/user-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("user-1"));
    }

    @Test
    void createUsesPostApiUsersAndReturnsLocation() throws Exception {
        when(createUserUseCase.execute(any())).thenReturn(user("user-1"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new CreateUserRequest(
                                        "Maria Gomez",
                                        "maria@example.com",
                                        "Secret123",
                                        "MEMBER"))))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/users/user-1"))
                .andExpect(jsonPath("$.id").value("user-1"));
    }

    @Test
    void updateUsesPutApiUsersId() throws Exception {
        when(updateUserUseCase.execute(any())).thenReturn(user("user-1"));

        mockMvc.perform(put("/api/users/user-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new UpdateUserRequest(
                                        "Maria Gomez",
                                        "maria@example.com",
                                        null,
                                        "MEMBER",
                                        "ACTIVE"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("user-1"));
    }

    @Test
    void deleteUsesDeleteApiUsersIdAndReturnsNoContentWhenDeleted() throws Exception {
        when(deleteUserUseCase.execute(any())).thenReturn(DeleteUserResult.deleted("user-1"));

        mockMvc.perform(delete("/api/users/user-1"))
                .andExpect(status().isNoContent());

        verify(deleteUserUseCase).execute(any());
    }

    @Test
    void deleteUsesDeleteApiUsersIdAndReturnsMessageWhenUserWasNotFound() throws Exception {
        when(deleteUserUseCase.execute(any())).thenReturn(DeleteUserResult.notFound("user-1"));

        mockMvc.perform(delete("/api/users/user-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.code").value("USER_NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("El usuario con id 'user-1' no existe o ya fue eliminado."));

        verify(deleteUserUseCase).execute(any());
    }

    @Test
    void loginUsesPostApiUsersLogin() throws Exception {
        when(loginUseCase.execute(any())).thenReturn(user("user-1"));

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                new LoginRequest("maria@example.com", "Secret123"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("maria@example.com"));
    }

    private static UserModel user(final String id) {
        return new UserModel(
                new UserId(id),
                new UserName("Maria Gomez"),
                new UserEmail("maria@example.com"),
                UserPassword.fromHash("stored-hash"),
                UserRole.MEMBER,
                UserStatus.ACTIVE,
                "2026-05-24T10:00:00",
                "2026-05-24T10:00:00");
    }
}
