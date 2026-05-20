package candidate.data.exception;

public class CantWriteJsonException extends RuntimeException {

	private static final long serialVersionUID = 1007L;

	public CantWriteJsonException(String message) {
		super(message);
	}
}
