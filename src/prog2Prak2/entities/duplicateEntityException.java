package prog2Prak2.entities;

public class duplicateEntityException extends RuntimeException{

	public duplicateEntityException() {
		super();
	}

	public duplicateEntityException(String message) {
		super(message);
	}
}
