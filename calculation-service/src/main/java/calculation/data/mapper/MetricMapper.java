package calculation.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import calculation.data.dto.SalaryForkDto;
import calculation.data.entity.Metric;
import calculation.data.event.CandidateCreatedEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetricMapper {

	Metric candidateCreatedEventToMetric(CandidateCreatedEvent candidateCreatedEvent);

	void setSalaryForkToMetric(SalaryForkDto salaryFork, @MappingTarget Metric metric);
}
