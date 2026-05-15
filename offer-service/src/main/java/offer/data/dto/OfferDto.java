package offer.data.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import offer.data.enums.Grade;

public record OfferDto(UUID offerId,

		Integer candidateId,

		Grade grade,

		Double experience,

		BigDecimal salary,

		LocalDateTime createDate) {

}
