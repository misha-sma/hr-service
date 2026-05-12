package offer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import offer.data.entity.Offer;

public interface OfferController {

	@GetMapping("/offer/{offerId}")
	Offer getOffer(@PathVariable UUID offerId);

	@GetMapping("/offers/{candidateId}")
	List<Offer> getAllOffersByCandidate(@PathVariable Integer candidateId);

	@GetMapping("/")
	String getHomePage();
}
