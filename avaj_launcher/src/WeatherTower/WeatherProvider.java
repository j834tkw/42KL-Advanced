package avaj_launcher.src.WeatherTower;

import avaj_launcher.src.Misc.Coordinates;

public class WeatherProvider {
	private static final WeatherProvider instance = new WeatherProvider();

	private WeatherProvider() {}

	public static WeatherProvider getInstance() {
        return instance;
    }

	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

	public String getCurrentWeather(Coordinates p_coordinates) {
		int seed = 12345;
		int value = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight() + seed;

		return weather[value % 4];
	}
}
