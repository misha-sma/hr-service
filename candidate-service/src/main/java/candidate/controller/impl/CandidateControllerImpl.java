package candidate.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import candidate.controller.CandidateController;
import candidate.data.dto.CreateCandidateDto;
import candidate.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CandidateControllerImpl implements CandidateController {

	private final CandidateService candidateService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/create")
	@Override
	public void createCandidate(@Valid @RequestBody CreateCandidateDto createCandidateDto) {
		System.out.println(createCandidateDto); 
		candidateService.createCandidate(createCandidateDto);
	}

	@GetMapping("/")
	public String getHomePage() {
		return "Candidate service works!!!";
	}
}
