package offer.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import offer.data.entity.Offer;
import offer.data.event.CalculationCompletedEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferMapper {

	@Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "offerId", expression = "java(java.util.UUID.randomUUID())")
	Offer metricDtoToOffer(CalculationCompletedEvent event);
}
