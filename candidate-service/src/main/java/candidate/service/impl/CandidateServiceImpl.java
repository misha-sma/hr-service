package candidate.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.ResponseDto;
import candidate.data.dto.UpdateCandidateDto;
import candidate.data.entity.Candidate;
import candidate.data.entity.Event;
import candidate.data.exception.CandidateNotExistException;
import candidate.data.mapper.CandidateMapper;
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
	private final ObjectMapper objectMapper;

	@Override
	@Transactional
	public ResponseDto createCandidate(CreateCandidateDto createCandidateDto) {
		Candidate candidate = candidateMapper.createCandidateDtoToCandidate(createCandidateDto);
		candidateRepository.save(candidate);
		Event event = new Event();
		event.setCandidateId(candidate.getCandidateId());
		event.setSent(false);
		event.setCreateDate(LocalDateTime.now());
		try {
			event.setMessage(objectMapper.writeValueAsString(candidate));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		eventRepository.save(event);
		return new ResponseDto(candidate.getCandidateId());
	}

	@Override
	@Transactional
	public void updateCandidate(UpdateCandidateDto updateCandidateDto) {
		Candidate candidate = candidateRepository.findById(updateCandidateDto.candidateId())
				.orElseThrow(() -> new CandidateNotExistException(
						"Candidate with id " + updateCandidateDto.candidateId() + " doesn't exist"));
		candidateMapper.updateCandidateFromDto(updateCandidateDto, candidate);
		candidateRepository.save(candidate);
		Event event = new Event();
		event.setCandidateId(candidate.getCandidateId());
		event.setSent(false);
		event.setCreateDate(LocalDateTime.now());
		try {
			event.setMessage(objectMapper.writeValueAsString(candidate));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		eventRepository.save(event);
	}
}
