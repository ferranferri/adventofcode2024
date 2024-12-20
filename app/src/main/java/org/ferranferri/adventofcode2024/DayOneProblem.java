package org.ferranferri.adventofcode2024;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class DayOneProblem extends ProblemSolver {


    public DayOneProblem(String inputFile) {
        super(inputFile);
    }

    public int solveProblemDayOnePartOne() {

        List<Integer> firstColumn = getColumn(lines, 0,true);
        List<Integer> secondColumn = getColumn(lines, 1,true);

        return IntStream.range(0, firstColumn.size())
                .map(index -> abs(firstColumn.get(index) - secondColumn.get(index)))
                .sum();

    }

    public int solveProblemDayOnePartTwo() {
        List<Integer> firstColumn = getColumn(lines, 0,false);
        List<Integer> secondColumn = getColumn(lines, 1,false);

        return firstColumn.stream()
                .mapToInt(element -> element * (int) secondColumn.stream()
                        .filter(n -> Objects.equals(n, element))
                        .count())
                .sum();

    }

    public List<Integer> getColumn(List<String> lines, int index, boolean sorted) {
        Stream<Integer> stream = lines.stream()
                .map(line -> line.split(" {3}")) // Split by 3 spaces
                .map(parts -> Integer.parseInt(parts[index])); // Parse the first column

        if (sorted) {
            stream = stream.sorted(); // Apply sorting if `sorted` is true
        }

        return stream.collect(Collectors.toList()); // Collect to a list
    }
}
