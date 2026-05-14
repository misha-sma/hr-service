package offer.service;

import offer.data.event.CalculationCompletedEvent;

public interface KafkaConsumer {

	void getEvent(CalculationCompletedEvent calculationCompletedEvent);
}
