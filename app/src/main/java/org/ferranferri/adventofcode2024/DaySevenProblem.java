package org.ferranferri.adventofcode2024;

import java.util.ArrayList;
import java.util.List;


public class DaySevenProblem extends ProblemSolver{
    public DaySevenProblem(String inputFile) {
        super(inputFile);
    }

    public long solveProblemDaySevenPartOne() {
        long counter = 0;
        for (String line : lines) {
            counter += checkOperationCombination(parseExpectedResult(line), parseEquationNumbers(line));
        }
        return counter;
    }

    public long solveProblemDaySevenPartTwo() {

        return 0;
    }

    private long checkOperationCombination(long expected, List<Long> operators) {
        if (checkOperatorCombination(expected, 0, operators, 0)) {
            return expected;
        }
        return 0;
    }


    private boolean checkOperatorCombination(long expected, long partial, List<Long> operators, int index) {

        if (index == operators.size() - 1) {
            if (partial * operators.get(index)  == expected) {
                return true;
            }
            return partial + operators.get(index) == expected;
        }

        return checkOperatorCombination(expected,   partial + operators.get(index), operators, index + 1)
                || checkOperatorCombination(expected, partial * operators.get(index), operators, index + 1);
    }


    private long parseExpectedResult(String line) {
        return Long.parseLong(line.split(":")[0]);
    }

    private List<Long> parseEquationNumbers(String line) {
        List<Long> numbers = new ArrayList<>();

        String[] numberStrings = line.split(":")[1].trim().split(" ");
        for (String number : numberStrings) {
            numbers.add(Long.parseLong(number));
        }
        return numbers;
    }

}
