package calculation.data.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import calculation.data.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {

	@JsonProperty("candidateId")
	private Integer candidateId;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("grade")
	private Grade grade;

	@JsonProperty("experience")
	private Double experience;

	@JsonProperty("currentSalary")
	private BigDecimal currentSalary;

	@JsonProperty("createDate")
	private LocalDateTime createDate;
}
