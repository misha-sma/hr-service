package candidate.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import candidate.service.KafkaProducer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

	@Value("${kafka.producer.topic}")
	private String createCandidateTopic;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void sendMessage(String message, Integer candidateId) {
		kafkaTemplate.send(createCandidateTopic, String.valueOf(candidateId), message);
	}
}