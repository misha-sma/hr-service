package calculation.service;

import calculation.data.event.CandidateCreatedEvent;

public interface CalculationService {

	void calculateMetics(CandidateCreatedEvent candidateCreatedEvent);
}
