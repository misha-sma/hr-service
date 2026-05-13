package candidate.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import candidate.data.entity.Event;
import candidate.data.event.CandidateCreatedEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

	@Mapping(target = "sent", constant = "false")
	@Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
	Event candidateCreatedEventToEvent(CandidateCreatedEvent candidateCreatedEvent);
}
