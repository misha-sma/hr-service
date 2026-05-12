package candidate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.ResponseDto;
import candidate.data.dto.UpdateCandidateDto;
import jakarta.validation.Valid;

public interface CandidateController {

	@PostMapping("/create")
	ResponseDto createCandidate(@Valid @RequestBody CreateCandidateDto createCandidateDto);

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update")
	void updateCandidate(@Valid @RequestBody UpdateCandidateDto updateCandidateDto);

	@GetMapping("/")
	String getHomePage();
}
