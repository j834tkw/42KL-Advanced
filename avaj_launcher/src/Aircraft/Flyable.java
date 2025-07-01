package avaj_launcher.src.Aircraft;

import avaj_launcher.src.WeatherTower.WeatherTower;

public interface Flyable {	
	void updateConditions();

	void registerTower(WeatherTower p_tower);
}
