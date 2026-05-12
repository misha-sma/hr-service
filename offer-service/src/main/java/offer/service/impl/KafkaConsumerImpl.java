package offer.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offer.data.dto.MetricDto;
import offer.service.KafkaConsumer;
import offer.service.OfferService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {

	private static final String KAFKA_TOPIC = "${kafka.consumer.topic}";
	private static final String KAFKA_CONSUMER_GROUP_ID = "${kafka.consumer.group-id}";

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	private final OfferService offerService;

	@Override
	@KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_CONSUMER_GROUP_ID)
	public void getMetric(String json) {
		log.info("Receive json: " + json);
		try {
			MetricDto metric = objectMapper.readValue(json, MetricDto.class);
			log.info("Receive metric: " + metric.toString());
			offerService.createOffer(metric);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
	}
}
