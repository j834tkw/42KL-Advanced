package avaj_launcher.src.AircraftSrc;

import avaj_launcher.src.WeatherTower.WeatherTower;

public abstract class Flyable {
	protected WeatherTower weatherTower;

	public abstract void updateConditions();

	public void registerTower(WeatherTower p_tower) {
		this.weatherTower = p_tower;
        this.weatherTower.register(this);
	};
}
