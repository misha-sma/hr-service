package calculation.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import calculation.data.dto.SalaryForkDto;
import calculation.data.entity.Metric;
import calculation.data.event.CandidateCreatedEvent;
import calculation.data.mapper.MetricMapper;
import calculation.service.CalculationService;
import calculation.service.EventService;
import calculation.service.KafkaProducer;
import calculation.service.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

	private final MetricService metricService;
	private final EventService eventService;
	private final MetricMapper metricMapper;
	private final KafkaProducer kafkaProducer;
	private final ObjectMapper objectMapper;

	@Value("${app.min-salary}")
	private BigDecimal minSalary;

	@Value("${app.junior-middle-threshold}")
	private BigDecimal juniorMiddleThreshold;

	@Value("${app.middle-senior-threshold}")
	private BigDecimal middleSeniorThreshold;

	@Value("${app.max-salary}")
	private BigDecimal maxSalary;

	@Override
	public void calculateMetics(CandidateCreatedEvent event) {
		CandidateCreatedEvent cachedEvent = eventService.getCandidateCreatedEventById(event.eventId());
		if (cachedEvent != null) {
			return;
		}
		eventService.saveCandidateCreatedEvent(event);
		Metric metric = metricMapper.candidateCreatedEventToMetric(event);
		SalaryForkDto salaryFork = getSalaryFork(event);
		metricMapper.setSalaryForkToMetric(salaryFork, metric);
		metricService.saveMetric(metric);
		try {
			String json = objectMapper.writeValueAsString(metric);
			kafkaProducer.sendMessage(json, metric.getCandidateId());
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
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
