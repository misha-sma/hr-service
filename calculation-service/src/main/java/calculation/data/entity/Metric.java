package calculation.data.entity;

import java.math.BigDecimal;

import calculation.data.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Metric {

	private Integer candidateId;

	private Grade grade;

	private Double experience;

	private BigDecimal minSalary;

	private BigDecimal maxSalary;
}
