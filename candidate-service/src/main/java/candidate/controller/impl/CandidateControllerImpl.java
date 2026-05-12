package candidate.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import candidate.controller.CandidateController;
import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.ResponseDto;
import candidate.data.dto.UpdateCandidateDto;
import candidate.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CandidateControllerImpl implements CandidateController {

	private final CandidateService candidateService;

	@Override
	@PostMapping("/create")
	public ResponseDto createCandidate(@Valid @RequestBody CreateCandidateDto createCandidateDto) {
		log.info("Post request: " + createCandidateDto);
		return candidateService.createCandidate(createCandidateDto);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update")
	public void updateCandidate(@Valid @RequestBody UpdateCandidateDto updateCandidateDto) {
		log.info("Put request: " + updateCandidateDto);
		candidateService.updateCandidate(updateCandidateDto);
	}

	@Override
	@GetMapping("/")
	public String getHomePage() {
		return "Candidate service works!!!";
	}
}
