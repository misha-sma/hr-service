package offer.service;

import java.util.List;
import java.util.UUID;

import offer.data.dto.MetricDto;
import offer.data.entity.Offer;

public interface OfferService {

	void createOffer(MetricDto metric);

	Offer getOffer(UUID offerId);

	List<Offer> getAllOffersByCandidate(Integer candidateId);
}
