package offer.service;

import java.util.List;
import java.util.UUID;

import offer.data.entity.Offer;
import offer.data.event.CalculationCompletedEvent;

public interface OfferService {

	void createOffer(CalculationCompletedEvent event);

	Offer getOffer(UUID offerId);

	List<Offer> getAllOffersByCandidate(Integer candidateId);
}
