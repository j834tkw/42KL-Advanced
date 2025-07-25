package avaj_launcher.src;

import java.util.HashMap;

import avaj_launcher.src.AircraftSrc.Aircraft;

public class Jetplane extends Aircraft{
	public Jetplane (long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	@Override
	public void updateConditions() {
		HashMap<String, String> msg = new HashMap<>();
		msg.put("SUN", "The INTENSE BLAZING SUNSHINE is upon my plane");
		msg.put("RAIN", "The UNENDING HEAVY THUNDERSTORM is upon my plane");
		msg.put("FOG", "The DARK CONCEALING MURK is upon my plane");
		msg.put("SNOW", "The BITTER ARCTIC FROSTBITE is upon my plane");

		String weather = this.weatherTower.getWeather(this.coordinates);

		switch(weather) {
			case "SUN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
			case "RAIN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
			case "FOG":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
			case "SNOW":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
		}

		Log.write(this.toString() + ": " + msg.get(weather));

		if (this.coordinates.getHeight() == 0) {
			this.weatherTower.unregister(this);
		}
	}

	public String toString() {
        return "Jetplane#" + name + " [id " + id + "]";
    }
}
