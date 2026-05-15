package offer.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import offer.controller.OfferController;
import offer.data.dto.OfferDto;
import offer.service.OfferService;

@RestController
@RequiredArgsConstructor
public class OfferControllerImpl implements OfferController {

	private final OfferService offerService;

	@Override
	@GetMapping("/offer/{offerId}")
	public OfferDto getOffer(@PathVariable UUID offerId) {
		return offerService.getOffer(offerId);
	}

	@Override
	@GetMapping("/offers/{candidateId}")
	public List<OfferDto> getAllOffersByCandidate(@PathVariable Integer candidateId) {
		return offerService.getAllOffersByCandidate(candidateId);
	}

	@Override
	@GetMapping("/")
	public String getHomePage() {
		return "Offer service works!!!";
	}
}
