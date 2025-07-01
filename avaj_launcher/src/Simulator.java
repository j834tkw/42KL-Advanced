package avaj_launcher.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import avaj_launcher.src.WeatherTower.WeatherTower;
import avaj_launcher.src.Aircraft.AircraftFactory;
import avaj_launcher.src.Misc.Coordinates;

public class Simulator {
	private static int totalIterations;
	private static WeatherTower weatherTower;

	public static void getSimulationIterations(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String firstLine = br.readLine();

            if (firstLine == null) {
                throw new IOException("ERROR: scenario.txt file is empty");
            }

            totalIterations = Integer.parseInt(firstLine.trim());
        }
    }

	public static void getAircrafts(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1)
					continue;

                String[] parts = line.trim().split("\\s+");

                if (parts.length != 5) {
                    throw new IllegalArgumentException("ERROR: Invalid aircraft line at line number " + lineNumber + ": " + line);
                }

                String type = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
				Coordinates coordinates = new Coordinates(longitude, latitude, height);

                AircraftFactory.newAircraft(type, name, coordinates);
            }
        }
    }

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				throw new Exception("ERROR: Provide a file path as a single argument.");
			}

			String filename = args[0];
			
			getSimulationIterations(filename);
			getAircrafts(filename);

			weatherTower = new WeatherTower();
			
			while (totalIterations-- > 0) {
				weatherTower.changeWeather();
			}

		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
