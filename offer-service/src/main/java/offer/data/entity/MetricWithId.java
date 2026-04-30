package offer.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import offer.data.enums.Grade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricWithId {

	private Integer candidateId;

	private Grade grade;

	private Double experience;
}
