package calculation.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import calculation.data.event.CalculationCompletedEvent;
import calculation.service.KafkaProducer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

	@Value("${spring.kafka.producer.topic}")
	private String completeCalculationTopic;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void sendMessage(CalculationCompletedEvent event) {
		kafkaTemplate.send(completeCalculationTopic, String.valueOf(event.getCandidateId()), event);
	}
}
