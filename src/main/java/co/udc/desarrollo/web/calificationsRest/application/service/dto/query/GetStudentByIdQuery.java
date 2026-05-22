package co.udc.desarrollo.web.calificationsRest.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetStudentByIdQuery(@NotBlank(message = "id no puede estar vacio") String id) {
}
