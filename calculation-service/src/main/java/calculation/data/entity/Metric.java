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
public class Metric {

	private Grade grade;

	private Double experience;
}
