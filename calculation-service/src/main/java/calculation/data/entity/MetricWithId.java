package calculation.data.entity;

import calculation.data.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricWithId {

	private Integer candidateId;

	private Grade grade;

	private Double experience;
}
