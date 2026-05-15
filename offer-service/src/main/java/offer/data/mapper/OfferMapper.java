package offer.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import offer.data.dto.OfferDto;
import offer.data.event.CalculationCompletedEvent;
import offer.entity.Offer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OfferMapper {

	@Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "offerId", expression = "java(java.util.UUID.randomUUID())")
	Offer calculationCompletedEventToOffer(CalculationCompletedEvent event);

	OfferDto offerToOfferDto(Offer offer);

	List<OfferDto> offersToOfferDtos(List<Offer> offer);
}
