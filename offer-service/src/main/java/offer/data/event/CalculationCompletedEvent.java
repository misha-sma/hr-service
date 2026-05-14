package offer.data.event;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import offer.data.enums.Grade;

@Document(collection = "event")
public record CalculationCompletedEvent(@Id UUID eventId,

		Integer candidateId,

		Grade grade,

		Double experience,

		BigDecimal minSalary,

		BigDecimal maxSalary) {
}
