package avaj_launcher.src.Exceptions;

public class InvalidMainArgumentsException extends Exception {
	public InvalidMainArgumentsException(String message) {
		super(message);
	}

	public InvalidMainArgumentsException() {
		super("ERROR: Provide a file path as a single argument");
	}
}
