package calculation.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import calculation.data.dto.CandidateDto;
import calculation.data.dto.SalaryForkDto;
import calculation.data.entity.Metric;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetricMapper {

	Metric candidateDtoToMetric(CandidateDto candidadte);

	void setSalaryForkToMetric(SalaryForkDto salaryFork, @MappingTarget Metric metric);
}
