package offer.service;

import java.util.List;
import java.util.UUID;

import offer.data.dto.OfferDto;
import offer.data.event.CalculationCompletedEvent;

public interface OfferService {

	void createOffer(CalculationCompletedEvent event);

	OfferDto getOffer(UUID offerId);

	List<OfferDto> getAllOffersByCandidate(Integer candidateId);
}
