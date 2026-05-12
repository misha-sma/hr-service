package candidate.data.exception;

public class CandidateNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1001L;

	public CandidateNotExistException(String message) {
		super(message);
	}
}
