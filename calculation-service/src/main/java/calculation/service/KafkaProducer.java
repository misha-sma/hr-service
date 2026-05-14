package calculation.service;

import calculation.data.event.CalculationCompletedEvent;

public interface KafkaProducer {

	void sendMessage(CalculationCompletedEvent event);
}
