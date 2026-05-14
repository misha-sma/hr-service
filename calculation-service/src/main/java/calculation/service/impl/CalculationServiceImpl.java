package calculation.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import calculation.data.dto.SalaryForkDto;
import calculation.data.event.CalculationCompletedEvent;
import calculation.data.event.CandidateCreatedEvent;
import calculation.data.mapper.EventMapper;
import calculation.service.CalculationService;
import calculation.service.InputEventService;
import calculation.service.KafkaProducer;
import calculation.service.OutputEventService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

	private final OutputEventService outputEventService;
	private final InputEventService inputEventService;
	private final EventMapper eventMapper;
	private final KafkaProducer kafkaProducer;

	@Value("${app.min-salary}")
	private BigDecimal minSalary;

	@Value("${app.junior-middle-threshold}")
	private BigDecimal juniorMiddleThreshold;

	@Value("${app.middle-senior-threshold}")
	private BigDecimal middleSeniorThreshold;

	@Value("${app.max-salary}")
	private BigDecimal maxSalary;

	@Override
	public void calculateMetics(CandidateCreatedEvent inputEvent) {
		CandidateCreatedEvent cachedEvent = inputEventService.getCandidateCreatedEventById(inputEvent.eventId());
		if (cachedEvent != null) {
			return;
		}
		inputEventService.saveCandidateCreatedEvent(inputEvent);
		CalculationCompletedEvent outputEvent = eventMapper
				.candidateCreatedEventToCalculationCompletedEvent(inputEvent);
		SalaryForkDto salaryFork = getSalaryFork(inputEvent);
		eventMapper.setSalaryForkToCalculationCompletedEvent(salaryFork, outputEvent);
		outputEventService.saveEvent(outputEvent);
		kafkaProducer.sendMessage(outputEvent);
	}

	private SalaryForkDto getSalaryFork(CandidateCreatedEvent event) {
		return switch (event.grade()) {
		case JUNIOR -> new SalaryForkDto(minSalary, juniorMiddleThreshold);
		case MIDDLE -> new SalaryForkDto(juniorMiddleThreshold, middleSeniorThreshold);
		case SENIOR -> new SalaryForkDto(middleSeniorThreshold, maxSalary);
		default -> new SalaryForkDto(minSalary, maxSalary);
		};
	}
}
