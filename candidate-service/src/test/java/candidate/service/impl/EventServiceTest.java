package candidate.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import candidate.data.entity.Event;
import candidate.repository.EventRepository;
import candidate.service.EventService;
import candidate.service.KafkaProducer;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	private EventService eventService;

	@Mock
	private EventRepository eventRepository;

	@Mock
	private KafkaProducer kafkaProducer;

	@BeforeEach
	void init() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		eventService = new EventServiceImpl(eventRepository, kafkaProducer);
		Class<?> clazz = eventService.getClass();
		Field privateField = clazz.getDeclaredField("eventService");
		privateField.setAccessible(true);
		privateField.set(eventService, eventService);
	}

	@Test
	void sendMessagesTest() {
		Event event = new Event(UUID.randomUUID(), 1, "{}", false, LocalDateTime.now());

		when(eventRepository.getOneEventToSending()).thenReturn(Optional.of(event));

		assertEquals(true, eventService.sendOneMessage());
		assertEquals(true, event.isSent());
	}

	@Test
	void sendMessagesTestFalse() {
		when(eventRepository.getOneEventToSending()).thenReturn(Optional.empty());

		assertDoesNotThrow(() -> eventService.sendMessages());
	}
}
