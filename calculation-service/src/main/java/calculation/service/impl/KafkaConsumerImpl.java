package calculation.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import calculation.data.event.CandidateCreatedEvent;
import calculation.service.CalculationService;
import calculation.service.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

	private static final String KAFKA_TOPIC = "${spring.kafka.consumer.topic}";
	private static final String KAFKA_CONSUMER_GROUP_ID = "${spring.kafka.consumer.group-id}";

	private final CalculationService calculationService;

	@Override
	@KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_CONSUMER_GROUP_ID, properties = {
			"spring.json.value.default.type=calculation.data.event.CandidateCreatedEvent" })
	public void getCandidate(CandidateCreatedEvent candidateCreatedEvent) {
		log.info("Receive candidate: " + candidateCreatedEvent.toString());
		calculationService.calculateMetics(candidateCreatedEvent);
	}
}
