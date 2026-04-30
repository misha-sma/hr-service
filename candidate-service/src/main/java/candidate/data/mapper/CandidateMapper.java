package candidate.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.entity.Candidate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

	@Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
	Candidate createCandidateDtoToCandidate(CreateCandidateDto createCandidateDto);
}
