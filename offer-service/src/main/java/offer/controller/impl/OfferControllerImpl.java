package offer.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import offer.controller.OfferController;
import offer.data.entity.Offer;
import offer.service.OfferService;

@RestController
@RequiredArgsConstructor
public class OfferControllerImpl implements OfferController {

	private final OfferService offerService;

	@GetMapping("/offer/{offerId}")
	@Override
	public Offer getOffer(@PathVariable Integer offerId) {
		return offerService.getOffer(offerId);
	}

	@GetMapping("/")
	public String getHomePage() {
		return "Offer service works!!!";
	}
}
