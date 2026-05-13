package calculation.service.impl;

import java.util.UUID;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import calculation.data.event.CandidateCreatedEvent;
import calculation.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Override
	@CachePut(value = "event", key = "#event.eventId")
	public CandidateCreatedEvent saveCandidateCreatedEvent(CandidateCreatedEvent event) {
		return event;
	}

	@Override
	@Cacheable("event")
	public CandidateCreatedEvent getCandidateCreatedEventById(UUID eventId) {
		return null;
	}
}
