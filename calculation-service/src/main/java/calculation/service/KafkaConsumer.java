package calculation.service;

import calculation.data.event.CandidateCreatedEvent;

public interface KafkaConsumer {

	void getCandidate(CandidateCreatedEvent candidateCreatedEvent);
}
