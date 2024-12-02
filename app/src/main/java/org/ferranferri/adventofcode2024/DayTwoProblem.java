package org.ferranferri.adventofcode2024;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayTwoProblem extends ProblemSolver {
    private static final Logger log = Logger.getLogger(DayTwoProblem.class.getName());
    private static final int MAX_DIFF = 3;

    public DayTwoProblem(String inputFile) {
        super(inputFile);
    }

    public int solveProblemDayTwoPartOne() {
        return (int) lines.stream().map(this::convertToIntList).filter(this::isSafe).count();
    }

    public boolean isSafe(List<Integer> entry) {
        if (entry.size() < 2) {
            log.warning("This is is too short: " + entry);
            return false;
        }
        if (entry.get(0) < entry.get(1)) {
            return isIncreasingSafely(entry);
        }
        return isDecreasingSafely(entry);
    }
    protected boolean isIncreasingSafely(List<Integer> entry) {
        for (int i = 0; i < entry.size() - 1; i++) {
            if (entry.get(i) >= entry.get(i + 1)) {
                return false;
            }
            if (isTooDifferent(entry.get(i), entry.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    protected boolean isDecreasingSafely(List<Integer> entry) {
        for (int i = 0; i < entry.size() - 1; i++) {
            if (entry.get(i) <= entry.get(i + 1)) {
                return false;
            }
            if (isTooDifferent(entry.get(i), entry.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean isTooDifferent(int a, int b) {
        return Math.abs(a - b) > MAX_DIFF;
    }

    private List<Integer> convertToIntList(String input) {
        // Split the string by spaces
        return Stream.of(input.split(" "))               // Create a stream from the array
                        .map(Integer::parseInt)  // Convert each string to an Integer
                        .collect(Collectors.toList());
    }
}
