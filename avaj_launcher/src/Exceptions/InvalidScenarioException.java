package avaj_launcher.src.Exceptions;

public class InvalidScenarioException extends Exception {
	public InvalidScenarioException(String message) {
        super(message);
    }

	public InvalidScenarioException() {
        super("ERROR: The given scenario is invalid");
    }
}
