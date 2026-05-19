package offer.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import offer.data.dto.OfferDto;
import offer.data.enums.Grade;
import offer.data.event.CalculationCompletedEvent;
import offer.data.exception.OfferNotExistException;
import offer.data.mapper.OfferMapper;
import offer.entity.Offer;
import offer.repository.EventRepository;
import offer.repository.OfferRepository;
import offer.service.OfferService;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

	private OfferService offerService;

	@Mock
	private OfferRepository offerRepository;

	@Mock
	private EventRepository eventRepository;

	@Mock
	private OfferMapper offerMapper;

	@BeforeEach
	void init() {
		offerService = new OfferServiceImpl(offerRepository, eventRepository, offerMapper);
	}

	@Test
	void createOfferTest() {
		BigDecimal minSalary = new BigDecimal(200000);
		BigDecimal maxSalary = new BigDecimal(1000000);
		CalculationCompletedEvent event = new CalculationCompletedEvent(UUID.randomUUID(), 1, Grade.SENIOR, 3.5,
				minSalary, maxSalary);
		Offer offer = new Offer(UUID.randomUUID(), 1, Grade.SENIOR, 3.5, null, LocalDateTime.now());

		when(eventRepository.findById(any())).thenReturn(Optional.empty());
		when(offerMapper.calculationCompletedEventToOffer(any())).thenReturn(offer);

		offerService.createOffer(event);
		int compareMinSalary = offer.getSalary().compareTo(minSalary);
		assertThat(compareMinSalary <= 0);
		int compareMaxSalary = offer.getSalary().compareTo(maxSalary);
		assertThat(compareMaxSalary >= 0);
	}

	@Test
	void createOfferTestDouble() {
		BigDecimal minSalary = new BigDecimal(200000);
		BigDecimal maxSalary = new BigDecimal(1000000);
		CalculationCompletedEvent event = new CalculationCompletedEvent(UUID.randomUUID(), 1, Grade.SENIOR, 3.5,
				minSalary, maxSalary);

		when(eventRepository.findById(any())).thenReturn(Optional.of(event));

		assertDoesNotThrow(() -> offerService.createOffer(event));
	}

	@Test
	void getOfferTest() {
		UUID offerId = UUID.randomUUID();
		Offer offer = new Offer(offerId, 1, Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());
		OfferDto offerDto = new OfferDto(offerId, 1, Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());

		when(offerRepository.findById(any())).thenReturn(Optional.of(offer));
		when(offerMapper.offerToOfferDto(any())).thenReturn(offerDto);

		assertEquals(offerDto, offerService.getOffer(offerId));
	}

	@Test
	void getOfferTestNotExist() {
		UUID offerId = UUID.randomUUID();

		when(offerRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(OfferNotExistException.class, () -> offerService.getOffer(offerId));
	}

	@Test
	void getAllOffersByCandidateTest() {
		UUID offerId = UUID.randomUUID();
		Integer candidateId = 1;
		Offer offer = new Offer(offerId, candidateId, Grade.SENIOR, 3.5, new BigDecimal(200000.12),
				LocalDateTime.now());
		List<Offer> offers = List.of(offer);
		OfferDto offerDto = new OfferDto(offerId, candidateId, Grade.SENIOR, 3.5, new BigDecimal(200000.12),
				LocalDateTime.now());
		List<OfferDto> offerDtos = List.of(offerDto);

		when(offerRepository.findAll(any(Example.class), any(Sort.class))).thenReturn(offers);
		when(offerMapper.offersToOfferDtos(any())).thenReturn(offerDtos);

		assertEquals(offerDtos, offerService.getAllOffersByCandidate(candidateId));
	}
}
