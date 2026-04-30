package offer.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import offer.data.enums.Grade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "offer")
public class Offer {

	@Id
	private Integer candidateId;

	private Grade grade;

	private Double experience;
}
