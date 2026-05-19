package candidate.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.ResponseDto;
import candidate.data.dto.UpdateCandidateDto;
import candidate.data.enums.Grade;
import candidate.service.CandidateService;

@WebMvcTest(CandidateControllerImpl.class)
@AutoConfigureMockMvc
public class CandidateControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CandidateService candidateService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createCandidateTest() throws Exception {
		ResponseDto responseDto = new ResponseDto(1);
		CreateCandidateDto createCandidateDto = new CreateCandidateDto("Vasily", "Pupkin", Grade.SENIOR, 3.5,
				new BigDecimal(200000.12));

		when(candidateService.createCandidate(any())).thenReturn(responseDto);

		mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createCandidateDto))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
	}

	@Test
	void updateCandidateTest() throws Exception {
		UpdateCandidateDto updateCandidateDto = new UpdateCandidateDto(1, "Vasily", "Pupkin", Grade.SENIOR, 3.5,
				new BigDecimal(200000.12));

		mockMvc.perform(put("/update").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateCandidateDto))).andExpect(status().isOk());
	}
}
