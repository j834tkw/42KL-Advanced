package avaj_launcher.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
    private static Log instance;
    private static final String FILE_NAME = "simulation.txt";
    private PrintWriter writer;

    private Log() {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, false)));
        } catch (IOException e) {
            System.err.println("Failed to open log file: " + e.getMessage());
        }
    }

    private static Log getInstance() {
        if (instance == null) {
            synchronized (Log.class) {
                if (instance == null) {
                    instance = new Log();
                }
            }
        }
        return instance;
    }

    public static void write(String message) {
        Log logger = getInstance();
        if (logger.writer != null) {
            logger.writer.println(message);
            logger.writer.flush();
        }
    }
}