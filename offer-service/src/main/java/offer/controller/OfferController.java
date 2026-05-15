package offer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import offer.data.dto.OfferDto;

public interface OfferController {

	@GetMapping("/offer/{offerId}")
	OfferDto getOffer(@PathVariable UUID offerId);

	@GetMapping("/offers/{candidateId}")
	List<OfferDto> getAllOffersByCandidate(@PathVariable Integer candidateId);

	@GetMapping("/")
	String getHomePage();
}
