package offer.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

//import calculation.data.entity.Candidate;
//import calculation.service.CalculationService;
//import calculation.service.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import offer.data.entity.MetricWithId;
import offer.service.KafkaConsumer;
import offer.service.OfferService;

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
		System.out.println("recieve message json: " + json);
		try {
			MetricWithId metricWithId = objectMapper.readValue(json, MetricWithId.class);
			System.out.println("metric= " + metricWithId.toString());
			offerService.createOffer(metricWithId);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
