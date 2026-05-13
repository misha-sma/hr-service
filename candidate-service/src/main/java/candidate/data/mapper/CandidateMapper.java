package candidate.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.UpdateCandidateDto;
import candidate.data.entity.Candidate;
import candidate.data.event.CandidateCreatedEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

	@Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
	Candidate createCandidateDtoToCandidate(CreateCandidateDto createCandidateDto);

	void updateCandidateFromDto(UpdateCandidateDto updateCandidateDto, @MappingTarget Candidate candidate);

	@Mapping(target = "eventId", expression = "java(java.util.UUID.randomUUID())")
	CandidateCreatedEvent candidateToCandidateCreatedEvent(Candidate candidate);
}
