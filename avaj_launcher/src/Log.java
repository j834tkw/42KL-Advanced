package avaj_launcher.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
    private static final Log instance = new Log();
    private static final String FILE_NAME = "./simulation.txt";
    private PrintWriter writer;

    private Log() {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, false)));
        } catch (IOException e) {
            System.err.println("ERROR: Failed to open log file: " + e.getMessage());
        }
    }

    public static Log getInstance() {
        return instance;
    }

    public static void write(String message) {
        Log logger = getInstance();
        if (logger.writer != null) {
            try {
                logger.writer.println(message);
                logger.writer.flush();
            } catch (Exception e) {
                System.err.println("ERROR: Failed to log to simulation.txt: " + e.getMessage());
            }
        } else {
            System.err.println("ERROR: Logger not initialized. Cannot write log.");
        }
    }
}