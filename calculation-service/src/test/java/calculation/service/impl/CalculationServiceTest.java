package calculation.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import calculation.data.enums.Grade;
import calculation.data.event.CalculationCompletedEvent;
import calculation.data.event.CandidateCreatedEvent;
import calculation.data.mapper.EventMapper;
import calculation.service.CalculationService;
import calculation.service.InputEventService;
import calculation.service.KafkaProducer;
import calculation.service.OutputEventService;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {

	private CalculationService calculationService;

	@Mock
	private OutputEventService outputEventService;

	@Mock
	private InputEventService inputEventService;

	@Mock
	private EventMapper eventMapper;

	@Mock
	private KafkaProducer kafkaProducer;

	@BeforeEach
	void init() {
		calculationService = new CalculationServiceImpl(outputEventService, inputEventService, eventMapper,
				kafkaProducer);
	}

	@Test
	void calculateMetricsTest() {
		CandidateCreatedEvent inputEvent = new CandidateCreatedEvent(UUID.randomUUID(), 1, "Vasily", "Pupkin",
				Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());
		CalculationCompletedEvent outputEvent = new CalculationCompletedEvent(UUID.randomUUID(), 1, Grade.SENIOR, 3.5,
				null, null);

		when(inputEventService.getCandidateCreatedEventById(any())).thenReturn(null);
		when(eventMapper.candidateCreatedEventToCalculationCompletedEvent(any())).thenReturn(outputEvent);

		assertDoesNotThrow(() -> calculationService.calculateMetrics(inputEvent));
	}

	@Test
	void calculateMetricsTestDouble() {
		CandidateCreatedEvent inputEvent = new CandidateCreatedEvent(UUID.randomUUID(), 1, "Vasily", "Pupkin",
				Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());

		when(inputEventService.getCandidateCreatedEventById(any())).thenReturn(inputEvent);

		assertDoesNotThrow(() -> calculationService.calculateMetrics(inputEvent));
	}
}
