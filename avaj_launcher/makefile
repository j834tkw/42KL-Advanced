MAIN_CLASS = avaj_launcher.src.Simulator
SCENARIO_FILE = scenario.txt
OUT_DIR = build

all:
	mkdir -p $(OUT_DIR)
	find src -name "*.java" > sources.txt
	javac -d $(OUT_DIR) @sources.txt
	java -cp $(OUT_DIR) $(MAIN_CLASS) $(SCENARIO_FILE)

run:
	java -cp $(OUT_DIR) $(MAIN_CLASS) $(SCENARIO_FILE)

clean:
	find $(OUT_DIR) -name "*.class" -type f -delete
	rm -f sources.txt
	find $(OUT_DIR) -type d -empty -delete 2>/dev/null || true
