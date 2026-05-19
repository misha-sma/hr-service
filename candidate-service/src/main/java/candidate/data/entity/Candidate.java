package candidate.data.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import candidate.data.enums.Grade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@AllArgsConstructor
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private Integer candidateId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "grade")
	@Enumerated(EnumType.STRING)
	private Grade grade;

	@Column(name = "experience")
	private Double experience;

	@Column(name = "current_salary")
	private BigDecimal currentSalary;

	@Column(name = "create_date")
	private LocalDateTime createDate;
}
