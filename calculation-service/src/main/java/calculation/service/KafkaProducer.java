package calculation.service;

public interface KafkaProducer {

	void sendMessage(String message, Integer key);
}
