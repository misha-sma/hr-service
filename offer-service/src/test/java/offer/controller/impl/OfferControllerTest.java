package offer.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import offer.data.dto.OfferDto;
import offer.data.enums.Grade;
import offer.service.OfferService;

@WebMvcTest(OfferControllerImpl.class)
@AutoConfigureMockMvc
public class OfferControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private OfferService offerService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getOfferTest() throws Exception {
		UUID offerId = UUID.randomUUID();
		OfferDto offerDto = new OfferDto(offerId, 1, Grade.SENIOR, 3.5, new BigDecimal(200000.12), LocalDateTime.now());

		when(offerService.getOffer(any())).thenReturn(offerDto);

		mockMvc.perform(get("/offer/{offerId}", offerId)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(objectMapper.writeValueAsString(offerDto)));
	}

	@Test
	void getAllOffersByCandidateTest() throws Exception {
		Integer candidateId = 1;
		OfferDto offerDto = new OfferDto(UUID.randomUUID(), candidateId, Grade.SENIOR, 3.5, new BigDecimal(200000.12),
				LocalDateTime.now());
		List<OfferDto> offers = List.of(offerDto);

		when(offerService.getAllOffersByCandidate(any())).thenReturn(offers);

		mockMvc.perform(get("/offers/{candidateId}", candidateId)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(objectMapper.writeValueAsString(offers)));
	}
}
