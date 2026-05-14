package calculation.service.impl;

import java.util.UUID;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import calculation.data.event.CandidateCreatedEvent;
import calculation.service.InputEventService;

@Service
public class InputEventServiceImpl implements InputEventService {

	@Override
	@CachePut(value = "input_event", key = "#event.eventId")
	public CandidateCreatedEvent saveCandidateCreatedEvent(CandidateCreatedEvent event) {
		return event;
	}

	@Override
	@Cacheable("input_event")
	public CandidateCreatedEvent getCandidateCreatedEventById(UUID eventId) {
		return null;
	}
}
