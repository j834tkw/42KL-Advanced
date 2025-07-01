package avaj_launcher.src.WeatherTower;

import avaj_launcher.src.Misc.Coordinates;

public class WeatherTower extends Tower {
	public String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
	}

	public void changeWeather() {
		this.conditionChanged();
	}
}
