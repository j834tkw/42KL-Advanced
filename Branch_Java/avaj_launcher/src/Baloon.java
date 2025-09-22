package avaj_launcher.src;

import java.util.HashMap;

import avaj_launcher.src.AircraftSrc.Aircraft;

public class Baloon extends Aircraft{
	public Baloon (long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}

	@Override
	public void updateConditions() {
		HashMap<String, String> msg = new HashMap<>();
		msg.put("SUN", "I shall COOK my baloon in BLAZING HEAT");
		msg.put("RAIN", "I shall MOISTURIZE my baloon in VIOLENT RAIN");
		msg.put("FOG", "I shall HIDE my baloon in DENSE MIST");
		msg.put("SNOW", "I shall FREEZE my baloon in FRIGID SNOW");

		String weather = this.weatherTower.getWeather(this.coordinates);

		switch(weather) {
			case "SUN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
			case "RAIN":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
			case "FOG":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
			case "SNOW":
				this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 13);
		}

		Log.write(this.toString() + ": " + msg.get(weather));

		if (this.coordinates.getHeight() == 0) {
			this.weatherTower.unregister(this);
		}
	}

	@Override
    public String toString() {
        return "Baloon#" + name + " [id " + id + "]";
    }
}
