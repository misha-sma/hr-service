package offer.data.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import offer.data.enums.Grade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricDto {

	private Integer candidateId;

	private Grade grade;

	private Double experience;

	private BigDecimal minSalary;

	private BigDecimal maxSalary;
}
