package org.ferranferri.adventofcode2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayFifthProblemTest {

    @Test
    void searchForRule() {
        DayFifthProblem dayFifthProblem = new DayFifthProblem("/inputs/input_day5.txt");
        dayFifthProblem.createSeparatedLists();
        assertTrue(dayFifthProblem.searchForRule("46", "32", dayFifthProblem.updateRules));
        assertFalse(dayFifthProblem.searchForRule("48", "32", dayFifthProblem.updateRules));
    }
}