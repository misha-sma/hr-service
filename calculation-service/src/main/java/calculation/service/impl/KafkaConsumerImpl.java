package calculation.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import calculation.data.dto.CandidateDto;
import calculation.service.CalculationService;
import calculation.service.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

	private static final String KAFKA_TOPIC = "${kafka.consumer.topic}";
	private static final String KAFKA_CONSUMER_GROUP_ID = "${kafka.consumer.group-id}";

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	private final CalculationService calculationService;

	@Override
	@KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_CONSUMER_GROUP_ID)
	public void getCandidate(String json) {
		log.info("Receive json: " + json);
		try {
			CandidateDto candidate = objectMapper.readValue(json, CandidateDto.class);
			log.info("Receive candidate: " + candidate.toString());
			calculationService.calculateMetics(candidate);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
	}
}
