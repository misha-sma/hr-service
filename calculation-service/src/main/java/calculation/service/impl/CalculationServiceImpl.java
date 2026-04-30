package calculation.service.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import calculation.data.entity.Candidate;
import calculation.data.entity.Metric;
import calculation.data.entity.MetricWithId;
import calculation.service.CalculationService;
import calculation.service.KafkaProducer;
import calculation.service.MetricService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

	private final MetricService metricService;
	private final KafkaProducer kafkaProducer;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Override
	public void calculateMetics(Candidate candidate) {
		Metric metric = new Metric();
		metric.setExperience(candidate.getExperience());
		metric.setGrade(candidate.getGrade());
		metricService.saveMetric(metric, candidate.getCandidateId());
		MetricWithId metricWithId = new MetricWithId();
		metricWithId.setCandidateId(candidate.getCandidateId());
		metricWithId.setExperience(candidate.getExperience());
		metricWithId.setGrade(candidate.getGrade());
		try {
			String json = objectMapper.writeValueAsString(metricWithId);
			kafkaProducer.sendMessage(json, metricWithId.getCandidateId());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
