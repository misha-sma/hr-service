package candidate.service;

import candidate.data.dto.CreateCandidateDto;
import candidate.data.dto.ResponseDto;
import candidate.data.dto.UpdateCandidateDto;

public interface CandidateService {

	ResponseDto createCandidate(CreateCandidateDto createCandidateDto);

	void updateCandidate(UpdateCandidateDto updateCandidateDto);
}
