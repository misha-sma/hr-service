package candidate.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import candidate.data.entity.Event;
import candidate.repository.EventRepository;
import candidate.service.EventService;
import candidate.service.KafkaProducer;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;
	private final KafkaProducer kafkaProducer;

	@Scheduled(initialDelay = 1000, fixedDelay = 2000)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Override
	@Transactional
	public void sendMessages() {
		List<Event> events = eventRepository.getEventsToSending();
		for (Event event : events) {
			kafkaProducer.sendMessage(event.getMessage(), event.getCandidateId());
			event.setSent(true);
		}
	}
}
