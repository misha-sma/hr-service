package calculation.service;

import java.util.UUID;

import calculation.data.event.CandidateCreatedEvent;

public interface EventService {

	CandidateCreatedEvent saveCandidateCreatedEvent(CandidateCreatedEvent event);

	CandidateCreatedEvent getCandidateCreatedEventById(UUID eventId);
}
