package candidate.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import candidate.data.entity.Event;
import candidate.repository.EventRepository;
import candidate.service.EventService;
import candidate.service.KafkaProducer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;
	private final KafkaProducer kafkaProducer;

	@Lazy
	@Autowired
	private EventService eventService;

	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 2000)
	public void sendMessages() {
		boolean isSent = false;
		do {
			isSent = eventService.sendOneMessage();
		} while (isSent);
	}

	@Override
	@Transactional
	public boolean sendOneMessage() {
		Optional<Event> eventOptional = eventRepository.getOneEventToSending();
		if (eventOptional.isEmpty()) {
			return false;
		}
		Event event = eventOptional.get();
		kafkaProducer.sendMessage(event.getMessage(), event.getCandidateId());
		event.setSent(true);
		return true;
	}
}
