package avaj_launcher.src.WeatherTower;

import java.util.ArrayList;
import java.util.List;

import avaj_launcher.src.Aircraft.Flyable;

public class Tower {
	private List<Flyable> observers = new ArrayList<Flyable>();

	public void register(Flyable p_flyable) {
		if (!observers.contains(p_flyable)) {
			observers.add(p_flyable);
		}
	}

	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
	}

	protected void conditionChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}
}
