package org.ferranferri.adventofcode2024;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DayTwoProblem extends ProblemSolver {
    private static final int MAX_DIFF = 3;

    public DayTwoProblem(String inputFile) {
        super(inputFile);
    }

    public int solveProblemDayTwoPartOne() {
        return (int) lines.stream().map(this::convertToIntList).filter(this::isSafe).count();
    }
    public int solveProblemDayTwoPartTwo() {
        return (int) lines.stream().map(this::convertToIntList).filter(this::isSafeWithTolerance).count();
    }

    private boolean isSafeWithTolerance(List<Integer> entry) {
        if (isSafe(entry)) {
            return true;
        }
        for (int i = 0; i < entry.size(); i++) {
            List<Integer> candidate = removeElementAtIndex(entry, i);
            if (isSafe(candidate)) {
                return true;
            }
        }
        return false;

    }

    private List<Integer> removeElementAtIndex(List<Integer> list, int index) {
        return IntStream.range(0, list.size())
                .filter(i -> i != index) // Exclude the specified index
                .mapToObj(list::get) // Get elements at all indices except the removed one
                .collect(Collectors.toList());
    }

    public boolean isSafe(List<Integer> entry) {
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
