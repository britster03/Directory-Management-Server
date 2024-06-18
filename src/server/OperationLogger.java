package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OperationLogger {
    private static final String LOG_FILE = "operations.log";

    public static synchronized void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}
