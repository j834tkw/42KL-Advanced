package avaj_launcher.src.Aircraft;

import java.util.HashMap;

import avaj_launcher.src.Log;
import avaj_launcher.src.Misc.Coordinates;
import avaj_launcher.src.WeatherTower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public Helicopter (long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	public void registerTower(WeatherTower p_tower) {
		this.weatherTower = p_tower;
		this.weatherTower.register(this);
	}

	public void updateConditions() {
		HashMap<String, String> msg = new HashMap<>();
		msg.put("SUN", "This CHOPPER will PIERCE the LIGHT of the SUN");
		msg.put("RAIN", "This CHOPPER will REND the TEARS of the HEAVENS");
		msg.put("FOG", "This CHOPPER will SLICE the DOMAIN of the UNKNOWN");
		msg.put("SNOW", "This CHOPPER will RIP the ESSENCE of the WINTER");

		String weather = this.weatherTower.getWeather(this.coordinates);

		switch(weather) {
			case "SUN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 5);
			case "RAIN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
			case "FOG":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
			case "SNOW":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
		}

		Log.write(this.toString() + ": " + msg.get(weather));

		if (this.coordinates.getHeight() == 0) {
			this.weatherTower.unregister(this);
		}
	}

	public String toString() {
        return "Helicopter #" + name + " [id " + id + "]";
    }
}
