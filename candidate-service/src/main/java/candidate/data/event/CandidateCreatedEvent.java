package candidate.data.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import candidate.data.enums.Grade;

public record CandidateCreatedEvent(UUID eventId,

		Integer candidateId,

		String firstName,

		String lastName,

		Grade grade,

		Double experience,

		BigDecimal currentSalary,

		LocalDateTime createDate) {

}
