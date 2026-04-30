package candidate.service;

public interface KafkaProducer {

	void sendMessage(String message, Integer candidateId);
}
