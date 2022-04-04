package prog2Prak2.entities;

public class missingEntityException extends RuntimeException{
	public missingEntityException() {
		super();
	}

	public missingEntityException(String message) {
		super(message);
	}
}
