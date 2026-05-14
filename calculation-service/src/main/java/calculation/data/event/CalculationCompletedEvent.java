package calculation.data.event;

import java.math.BigDecimal;
import java.util.UUID;

import calculation.data.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculationCompletedEvent {
	private UUID eventId;

	private Integer candidateId;

	private Grade grade;

	private Double experience;

	private BigDecimal minSalary;

	private BigDecimal maxSalary;
}
