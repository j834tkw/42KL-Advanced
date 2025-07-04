package avaj_launcher.src.Exceptions;

public class InvalidSimulationIterationException extends Exception {
	public InvalidSimulationIterationException(String message) {
		super(message);
	}

	public InvalidSimulationIterationException() {
		super("ERROR: The number of simulation iterations cannot be less than 0");
	}
}
