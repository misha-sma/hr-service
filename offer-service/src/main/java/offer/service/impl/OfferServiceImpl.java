package offer.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import offer.data.entity.MetricWithId;
import offer.data.entity.Offer;
import offer.repository.OfferRepository;
import offer.service.OfferService;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

	private final OfferRepository offerRepository;

	@Override
	public void createOffer(MetricWithId metricWithId) {
		Offer offer = new Offer();
		offer.setCandidateId(metricWithId.getCandidateId());
		offer.setExperience(metricWithId.getExperience());
		offer.setGrade(metricWithId.getGrade());
		offerRepository.save(offer);
		System.out.println("Offer saved!!!");
	}

	@Override
	public Offer getOffer(Integer offerId) {
		return offerRepository.findById(offerId).orElseThrow();
	}
}
