MAIN_CLASS = avaj_launcher.src.Simulator
SCENARIO_FILE = scenario.txt

all:
	find src -name "*.java" > sources.txt
	javac -d . @sources.txt
	java -cp . $(MAIN_CLASS) $(SCENARIO_FILE)

run:
	java -cp . $(MAIN_CLASS) $(SCENARIO_FILE)

clean:
	find . -name "*.class" -type f -delete
	rm -f sources.txt
	find ./avaj_launcher -type d -empty -delete 2>/dev/null || true
