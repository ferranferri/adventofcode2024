package org.ferranferri.adventofcode2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ProblemSolver {
    protected List<String> lines;

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
    }
}
