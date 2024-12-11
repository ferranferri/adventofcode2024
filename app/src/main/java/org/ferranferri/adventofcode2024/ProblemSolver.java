package org.ferranferri.adventofcode2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.*;

public class ProblemSolver {
    protected List<String> lines;
    protected Logger logger = Logger.getLogger(ProblemSolver.class.getName());

    public ProblemSolver(String inputFile) {
        // Load the file from resources using getClass().getResource()
        String filePath = Objects.requireNonNull(App.class.getResource(inputFile)).getPath();
        if (filePath != null) {

            try {
                lines = Files.readAllLines(Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // Disable parent handlers to avoid duplicate output
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.WARNING);

        // Create a console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();

        // Set the custom formatter
        consoleHandler.setFormatter(new Formatter() {
            public String format(LogRecord record) {
                return String.format("[%s] %s%n", record.getLevel(), record.getMessage());
            }
        });
        logger.addHandler(consoleHandler);
    }
}
