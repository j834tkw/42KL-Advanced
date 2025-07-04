package avaj_launcher.src.AircraftSrc;

import avaj_launcher.src.Coordinates;

public class Aircraft extends Flyable{
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	@Override
	public void updateConditions() {};

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinate;
	}
}
