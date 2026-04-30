package candidate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import candidate.data.dto.CreateCandidateDto;
import jakarta.validation.Valid;

public interface CandidateController {

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/create")
	void createCandidate(@Valid @RequestBody CreateCandidateDto createCandidateDto);

	@GetMapping("/")
	String getHomePage();
}
