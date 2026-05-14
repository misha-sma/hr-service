package offer.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offer.data.event.CalculationCompletedEvent;
import offer.service.KafkaConsumer;
import offer.service.OfferService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

	private static final String KAFKA_TOPIC = "${spring.kafka.consumer.topic}";
	private static final String KAFKA_CONSUMER_GROUP_ID = "${spring.kafka.consumer.group-id}";

	private final OfferService offerService;

	@Override
	@KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_CONSUMER_GROUP_ID, properties = {
			"spring.json.value.default.type=offer.data.event.CalculationCompletedEvent" })
	public void getEvent(CalculationCompletedEvent calculationCompletedEvent) {
		log.info("Receive event: " + calculationCompletedEvent.toString());
		offerService.createOffer(calculationCompletedEvent);
	}
}
