package candidate.data.dto;

import java.math.BigDecimal;

import candidate.data.enums.Grade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateCandidateDto(@NotBlank(message = "Имя пользователя не может быть пустым.") String firstName,

		@NotBlank(message = "Фамилия пользователя не может быть пустая.") String lastName,

		// @NotBlank
		Grade grade,

		@PositiveOrZero(message = "Опыт не может быть отрицательным.") Double experience,

		@PositiveOrZero(message = "Зарплата не может быть отрицательная.") BigDecimal currentSalary) {
}
