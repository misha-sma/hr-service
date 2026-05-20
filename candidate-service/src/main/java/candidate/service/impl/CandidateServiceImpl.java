package candidate.service.impl;

import candidate.data.exception.CantWriteJsonException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.ResponseDto;
import candidate.data.dto.UpdateCandidateDto;
import candidate.data.entity.Candidate;
import candidate.data.entity.Event;
import candidate.data.event.CandidateCreatedEvent;
import candidate.data.exception.CandidateNotExistException;
import candidate.data.mapper.CandidateMapper;
import candidate.data.mapper.EventMapper;
import candidate.repository.CandidateRepository;
import candidate.repository.EventRepository;
import candidate.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

	private final CandidateRepository candidateRepository;
	private final EventRepository eventRepository;

	private final CandidateMapper candidateMapper;
	private final EventMapper eventMapper;

	private final ObjectMapper objectMapper;

	@Override
	@Transactional
	public ResponseDto createCandidate(CreateCandidateDto createCandidateDto) {
		Candidate candidate = candidateMapper.createCandidateDtoToCandidate(createCandidateDto);
		saveCandidateAndEvent(candidate);
		return new ResponseDto(candidate.getCandidateId());
	}

	@Override
	@Transactional
	public void updateCandidate(UpdateCandidateDto updateCandidateDto) {
		Candidate candidate = candidateRepository.findById(updateCandidateDto.candidateId())
				.orElseThrow(() -> new CandidateNotExistException(
						"Candidate with id " + updateCandidateDto.candidateId() + " doesn't exist"));
		candidateMapper.updateCandidateFromDto(updateCandidateDto, candidate);
		saveCandidateAndEvent(candidate);
	}

	private void saveCandidateAndEvent(Candidate candidate) {
		candidateRepository.save(candidate);
		CandidateCreatedEvent candidateCreatedEvent = candidateMapper.candidateToCandidateCreatedEvent(candidate);
		Event event = eventMapper.candidateCreatedEventToEvent(candidateCreatedEvent);
		try {
			event.setMessage(objectMapper.writeValueAsString(candidateCreatedEvent));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
            throw new CantWriteJsonException("Can't write json with kafka event");
		}
		eventRepository.save(event);
	}
}
