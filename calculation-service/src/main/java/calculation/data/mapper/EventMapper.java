package calculation.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import calculation.data.dto.SalaryForkDto;
import calculation.data.event.CalculationCompletedEvent;
import calculation.data.event.CandidateCreatedEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

	CalculationCompletedEvent candidateCreatedEventToCalculationCompletedEvent(CandidateCreatedEvent event);

	void setSalaryForkToCalculationCompletedEvent(SalaryForkDto salaryFork,
			@MappingTarget CalculationCompletedEvent event);
}
