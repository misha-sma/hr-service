package candidate.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.UpdateCandidateDto;
import candidate.data.entity.Candidate;
import candidate.data.entity.Event;
import candidate.data.enums.Grade;
import candidate.data.event.CandidateCreatedEvent;
import candidate.data.exception.CandidateNotExistException;
import candidate.data.mapper.CandidateMapper;
import candidate.data.mapper.EventMapper;
import candidate.repository.CandidateRepository;
import candidate.repository.EventRepository;
import candidate.service.CandidateService;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

	private CandidateService candidateService;

	@Mock
	private CandidateMapper candidateMapper;

	@Mock
	private EventMapper eventMapper;

	@Mock
	private CandidateRepository candidateRepository;

	@Mock
	private EventRepository eventRepository;

	@BeforeEach
	void init() {
		candidateService = new CandidateServiceImpl(candidateRepository, eventRepository, candidateMapper, eventMapper,
				new ObjectMapper().registerModule(new JavaTimeModule()));
	}

	@Test
	void createCandidateTest() {
		CreateCandidateDto createCandidateDto = new CreateCandidateDto("Vasily", "Pupkin", Grade.SENIOR, 3.5,
				new BigDecimal(200000.12));
		Candidate candidate = new Candidate(1, "Vasily", "Pupkin", Grade.SENIOR, 3.5, new BigDecimal(200000.12),
				LocalDateTime.now());
		CandidateCreatedEvent candidateCreatedEvent = new CandidateCreatedEvent(UUID.randomUUID(), 1, "Vasily",
				"Pupkin", Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());
		Event event = new Event(UUID.randomUUID(), 1, "{}", false, LocalDateTime.now());

		when(candidateMapper.createCandidateDtoToCandidate(any())).thenReturn(candidate);
		when(candidateMapper.candidateToCandidateCreatedEvent(any())).thenReturn(candidateCreatedEvent);
		when(eventMapper.candidateCreatedEventToEvent(any())).thenReturn(event);

		assertEquals(1, candidateService.createCandidate(createCandidateDto).candidateId());
	}

	@Test
	void updateCandidateTest() {
		UpdateCandidateDto updateCandidateDto = new UpdateCandidateDto(1, "Vasily", "Pupkin", Grade.SENIOR, 3.5,
				new BigDecimal(200000.12));
		Candidate candidate = new Candidate(1, "Vasily", "Pupkin", Grade.SENIOR, 3.5, new BigDecimal(200000.12),
				LocalDateTime.now());
		CandidateCreatedEvent candidateCreatedEvent = new CandidateCreatedEvent(UUID.randomUUID(), 1, "Vasily",
				"Pupkin", Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());
		Event event = new Event(UUID.randomUUID(), 1, "{}", false, LocalDateTime.now());

		when(candidateRepository.findById(any())).thenReturn(Optional.of(candidate));
		when(candidateMapper.candidateToCandidateCreatedEvent(any())).thenReturn(candidateCreatedEvent);
		when(eventMapper.candidateCreatedEventToEvent(any())).thenReturn(event);

		assertDoesNotThrow(() -> candidateService.updateCandidate(updateCandidateDto));
	}

	@Test
	void updateCandidateTestFail() {
		UpdateCandidateDto updateCandidateDto = new UpdateCandidateDto(1, "Vasily", "Pupkin", Grade.SENIOR, 3.5,
				new BigDecimal(200000.12));

		when(candidateRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(CandidateNotExistException.class, () -> candidateService.updateCandidate(updateCandidateDto));
	}
}
