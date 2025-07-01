package avaj_launcher.src.WeatherTower;

import java.util.ArrayList;
import java.util.List;

import avaj_launcher.src.Log;
import avaj_launcher.src.Aircraft.Flyable;

public class Tower {
	private List<Flyable> observers = new ArrayList<Flyable>();

	public void register(Flyable p_flyable) {
		System.out.println("asasasass");
		if (!observers.contains(p_flyable)) {
			observers.add(p_flyable);
			Log.write("A new aircraft has been registered. Welcome to the Tower in Between, " + p_flyable.toString());
		}
		System.out.println("asasasass");
	}

	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
		Log.write(p_flyable.toString() + "has landed. The Tower in Between will no longer tracking the aircraft");
	}

	protected void conditionChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}
}
