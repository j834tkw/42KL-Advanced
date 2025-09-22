package avaj_launcher.src.Exceptions;

public class InvalidAircraftException extends Exception {
	public InvalidAircraftException(String message) {
		super(message);
	}

	public InvalidAircraftException() {
		super("ERROR: Unknown aircraft type: ");
	}
}
