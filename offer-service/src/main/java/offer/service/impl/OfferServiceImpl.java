package offer.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offer.data.entity.Offer;
import offer.data.event.CalculationCompletedEvent;
import offer.data.exception.OfferNotExistException;
import offer.data.mapper.OfferMapper;
import offer.repository.EventRepository;
import offer.repository.OfferRepository;
import offer.service.OfferService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

	private final OfferRepository offerRepository;
	private final EventRepository eventRepository;
	private final OfferMapper offerMapper;

	@Override
	public void createOffer(CalculationCompletedEvent event) {
		Optional<CalculationCompletedEvent> oldEventOptional = eventRepository.findById(event.eventId());
		if (oldEventOptional.isPresent()) {
			return;
		}
		eventRepository.save(event);
		Offer offer = offerMapper.metricDtoToOffer(event);
		BigDecimal salary = new BigDecimal(event.minSalary().doubleValue()
				+ Math.random() * (event.maxSalary().doubleValue() - event.minSalary().doubleValue()))
				.setScale(2, RoundingMode.HALF_UP);
		offer.setSalary(salary);
		offerRepository.save(offer);
		log.info("Offer saved!!! offerId=" + offer.getOfferId() + " candidateId=" + offer.getCandidateId());
	}

	@Override
	public Offer getOffer(UUID offerId) {
		return offerRepository.findById(offerId)
				.orElseThrow(() -> new OfferNotExistException("Offer with id=" + offerId + " doesn't exist"));
	}

	@Override
	public List<Offer> getAllOffersByCandidate(Integer candidateId) {
		Offer offer = new Offer();
		offer.setCandidateId(candidateId);
		return offerRepository.findAll(Example.of(offer), Sort.by(List.of(new Order(Direction.DESC, "createDate"))));
	}
}
