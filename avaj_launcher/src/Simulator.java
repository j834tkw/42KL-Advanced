package avaj_launcher.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import avaj_launcher.src.WeatherTower.WeatherTower;
import avaj_launcher.src.Aircraft.AircraftFactory;
import avaj_launcher.src.Exceptions.InvalidAircraftException;
import avaj_launcher.src.Exceptions.InvalidMainArgumentsException;
import avaj_launcher.src.Exceptions.InvalidScenarioException;
import avaj_launcher.src.Exceptions.InvalidSimulationIterationException;
import avaj_launcher.src.Misc.Coordinates;

public class Simulator {
	private static int totalIterations;
	private static WeatherTower weatherTower;

	public static void getSimulationIterations(String filename) throws IOException, InvalidSimulationIterationException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String firstLine = br.readLine();

            if (firstLine == null) {
                throw new IOException("ERROR: scenario.txt file is empty");
            }

            totalIterations = Integer.parseInt(firstLine.trim());

			if (totalIterations < 0) {
				throw new InvalidSimulationIterationException("ERROR: The number of simulation iterations cannot be less than 0");
			}
        }
    }

	public static void getAircrafts(String filename) throws IOException, InvalidScenarioException, InvalidAircraftException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber == 1)
					continue;

                String[] parts = line.trim().split("\\s+");

                if (parts.length != 5) {
                    throw new InvalidScenarioException("ERROR: Invalid line at line number " + lineNumber + ": " + line);
                }

                String type = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);

				if (height <= 0)
					throw new InvalidScenarioException("ERROR: Height cannot start at 0 and below");

				Coordinates coordinates = new Coordinates(longitude, latitude, height);

                AircraftFactory.getInstance().newAircraft(type, name, coordinates).registerTower(weatherTower);
            }
        }
    }

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				throw new InvalidMainArgumentsException("ERROR: Provide a file path as a single argument");
			}

			String filename = args[0];
			weatherTower = new WeatherTower();
			
			System.out.println("PROGRESS: Parsing simulation iterations...");
			getSimulationIterations(filename);
			System.out.println("PROGRESS: Finished parsing simulation iterations");
			System.out.println("PROGRESS: Parsing aircrafts...");
			getAircrafts(filename);
			System.out.println("PROGRESS: Finished parsing aircrafts");

			
			System.out.println("PROGRESS: Running Scenario...");
			while (totalIterations-- > 0) {
				weatherTower.changeWeather();
			}
			System.out.println("PROGRESS: Scenario run complete");

		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
