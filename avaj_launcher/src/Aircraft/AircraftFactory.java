package avaj_launcher.src.Aircraft;

import avaj_launcher.src.Misc.Coordinates;

public class AircraftFactory {
	private static final AircraftFactory instance = new AircraftFactory();
	private static long idCounter = 0;

	public static AircraftFactory getInstance() {
        return instance;
    }

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		long id = ++idCounter;
		
		switch (p_type) {
            case "Baloon":
                return new Baloon(id, p_name, p_coordinates);
            case "JetPlane":
                return new Jetplane(id, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(id, p_name, p_coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
        }
	}
}
