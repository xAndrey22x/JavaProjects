package exceptions;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
	public InvalidInputException() {

	}

	public InvalidInputException(String msg) {
		super(msg);
	}
}
