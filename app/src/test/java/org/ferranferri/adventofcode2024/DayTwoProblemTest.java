package org.ferranferri.adventofcode2024;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DayTwoProblemTest {

    @Test
    void isIncreasingSafely() {
        DayTwoProblem dayTwoProblem = new DayTwoProblem("/inputs/input_day2.txt");
        List<Integer> input = List.of(1,2,3,4,5,6,7,8,9,12);
        assertTrue(dayTwoProblem.isIncreasingSafely(input));
    }
}