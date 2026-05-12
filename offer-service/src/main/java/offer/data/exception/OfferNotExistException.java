package offer.data.exception;

public class OfferNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1002L;

	public OfferNotExistException(String message) {
		super(message);
	}
}
