package offer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import offer.data.entity.Offer;

public interface OfferController {

	@GetMapping("/offer/{offerId}")
	Offer getOffer(@PathVariable Integer offerId);

	@GetMapping("/")
	String getHomePage();
}
