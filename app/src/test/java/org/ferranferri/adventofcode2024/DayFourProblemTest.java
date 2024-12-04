package org.ferranferri.adventofcode2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DayFourProblemTest {

    @Test
    void checkCharAtCoordinate() {
        DayFourProblem dayFourProblem = new DayFourProblem("/inputs/input_day4.txt");
        assertTrue(dayFourProblem.checkCharAtCoordinate(0, 0, "X"));
        assertTrue(dayFourProblem.checkCharAtCoordinate(1, 0, "S"));
        assertTrue(dayFourProblem.checkCharAtCoordinate(2, 0, "M"));
        assertTrue(dayFourProblem.checkCharAtCoordinate(0, 0, "X"));
        assertTrue(dayFourProblem.checkCharAtCoordinate(0, 1, "M"));
        assertTrue(dayFourProblem.checkCharAtCoordinate(0, 2, "A"));
    }
}