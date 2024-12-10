package org.ferranferri.adventofcode2024;

import java.util.ArrayList;
import java.util.List;

public class DayFifthProblem extends ProblemSolver{

    protected List<String> updatePageSet;
    protected List<String> updateRules;

    public DayFifthProblem(String inputFile) {
        super(inputFile);

    }

    protected void createSeparatedLists() {
        List<List<String>> separatedLists = separateByEmptyString(lines);
        updateRules = separatedLists.getFirst();
        updatePageSet = separatedLists.getLast();
    }
    public int solveProblemDayFifthPartOne() {

        createSeparatedLists();
        int counter = 0;
        for(String updatePageString : updatePageSet) {
            List<String> updatePageList = convertStringToStringList(updatePageString);
            if (checkListIsCorrect(updatePageList)) {
                // get the middle point
                String middleValue = updatePageList.get(updatePageList.size() / 2 );
                counter += Integer.parseInt(middleValue);
            }

        }

        return counter;
    }

    public int solveProblemDayFifthPartTwo() {

        createSeparatedLists();
        int counter = 0;
        for(String updatePageString : updatePageSet) {
            List<String> updatePageList = convertStringToStringList(updatePageString);
            if (!checkListIsCorrect(updatePageList)) {
                List<String> fixedList = fixList(updatePageList);
                // get the middle point
                String middleValue = fixedList.get(fixedList.size() / 2 );
                counter += Integer.parseInt(middleValue);
            }

        }

        return counter;
    }

    private List<String> fixList(List<String> updatePageList) {
        for (int i = 0; i < updatePageList.size(); i++) {
            for (int j = i; j < updatePageList.size() ; j++) {
                if (searchForRule(updatePageList.get(j), updatePageList.get(i), updateRules)) {
                    swapElements(j, i, updatePageList);
                }
            }
        }
        return updatePageList;
    }

    private void swapElements(int a, int b, List<String> updatePageList) {

        // Swap the elements
        String temp = updatePageList.get(a);
        updatePageList.set(a, updatePageList.get(b));
        updatePageList.set(b, temp);

        // Join the array back into a string
    }

    private boolean checkListIsCorrect(List<String> updatePageList) {
        for (int i = 0; i < updatePageList.size(); i++) {
            for (int j = i; j < updatePageList.size() ; j++) {
                if (searchForRule(updatePageList.get(j), updatePageList.get(i), updateRules)) {
                    logger.info("List " + updatePageList + " for entry " + updatePageList.get(i) + " found rule " + updatePageList.get(j) + "|" + updatePageList.get(i));
                    return false;
                }
            }
        }

        logger.info("List " + updatePageList+ " is detected as correct");
        return true;
    }

    protected boolean searchForRule(String before, String after, List<String> updateRules) {
        // create rule and check if exists
        String rule = before + "|" + after;
        return updateRules.contains(rule);
    }

    private static List<String> convertStringToStringList(String input) {
        // Split the string by commas
        String[] parts = input.split(",");

        // Create an ArrayList and populate it with parsed numbers (as string)
        List<String> numberList = new ArrayList<>();
        for (String part : parts) {
            numberList.add(part.trim());
        }
        return numberList;
    }
    private static List<List<String>> separateByEmptyString(List<String> input) {
        List<String> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();
        boolean foundEmptyString = false;

        for (String line : input) {
            if (!foundEmptyString) {
                if (line.isEmpty()) {
                    foundEmptyString = true;
                } else {
                    firstList.add(line);
                }
            } else {
                secondList.add(line);
            }
        }

        List<List<String>> result = new ArrayList<>();
        result.add(firstList);
        result.add(secondList);

        return result;
    }
}
