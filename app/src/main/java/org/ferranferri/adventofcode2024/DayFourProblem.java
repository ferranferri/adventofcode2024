package org.ferranferri.adventofcode2024;

public class DayFourProblem  extends ProblemSolver{
    private final int MAX_SIZE = 140;
    public DayFourProblem(String inputFile) {
        super(inputFile);
    }

    public int solveProblemDayFourPartTwo() {
        int counter = 0;
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                if (checkCharAtCoordinate(i, j, "A")) {
                    if (checkXMas(i, j)) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    public int solveProblemDayFourPartOne() {
        int count = 0;
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                if (checkCharAtCoordinate(i, j, "M")) {
                    count += findSurroundingWord(i, j);

                }
            }
        }
        return count;
    }

    protected boolean checkXMas(int x, int y) {
        // diagonal one
        boolean d1 = (checkCharAtCoordinate(x -1, y -1, "M") && checkCharAtCoordinate(x + 1, y + 1, "S"))
                || (checkCharAtCoordinate(x -1, y -1, "S") && checkCharAtCoordinate(x + 1, y + 1, "M"));
        boolean d2 = (checkCharAtCoordinate(x + 1, y - 1, "M") && checkCharAtCoordinate(x - 1, y + 1, "S")
                || (checkCharAtCoordinate(x + 1, y - 1, "S") && checkCharAtCoordinate(x - 1, y + 1, "M")));
        return d1 && d2;
    }

    // iven a 2D index, that is an M get surrounding elements
    private int findSurroundingWord(int x, int y) {
        int count = 0;
        // X is in North then AS is in south
        if (checkCharAtCoordinate(x, y - 1, "X")) {
            if (checkCharAtCoordinate(x, y + 1, "A") && checkCharAtCoordinate(x, y + 2, "S")) {
                count++;
            }
        }
        // X is in NorthEast then AS is in SouthWest
        if (checkCharAtCoordinate(x + 1, y - 1, "X")) {
            if (checkCharAtCoordinate(x - 1, y + 1, "A") && checkCharAtCoordinate(x - 2, y + 2, "S")) {
                count++;

            }
        }
        // X is in East then AS is in West
        if (checkCharAtCoordinate(x + 1, y, "X")) {
            if (checkCharAtCoordinate(x - 1, y, "A") && checkCharAtCoordinate(x - 2, y, "S")) {
                count++;

            }
        }
        // X is in SouthEast then AS is in NorthWest
        if (checkCharAtCoordinate(x + 1, y + 1, "X")) {
            if (checkCharAtCoordinate(x - 1, y - 1, "A") && checkCharAtCoordinate(x - 2, y - 2, "S")) {
                count++;
            }
        }
        // X is in South then AS is in North
        if (checkCharAtCoordinate(x, y + 1, "X")) {
            if (checkCharAtCoordinate(x, y - 1, "A") && checkCharAtCoordinate(x, y - 2, "S")) {
                count++;
            }
        }
        // X is in SouthWest then AS is in NorthEast
        if (checkCharAtCoordinate(x - 1, y + 1, "X")) {
            if (checkCharAtCoordinate(x + 1, y - 1, "A") && checkCharAtCoordinate(x + 2, y - 2, "S")) {
                count++;
            }
        }
        // X is in West then AS is in East
        if (checkCharAtCoordinate(x - 1, y, "X")) {
            if (checkCharAtCoordinate(x + 1, y, "A") && checkCharAtCoordinate(x + 2, y, "S")) {
                count++;
            }
        }
        // X is in NorthWest then AS is in SouthEast
        if (checkCharAtCoordinate(x - 1, y - 1, "X")) {
            if (checkCharAtCoordinate(x + 1, y + 1, "A") && checkCharAtCoordinate(x + 2, y + 2, "S")) {
                count++;
            }
        }
        return count;
    }


    protected boolean checkCharAtCoordinate(int x, int y, String character) {
        if (x < 0 || x >= MAX_SIZE || y < 0 || y >= MAX_SIZE) {
            return false;
        }
        return lines.get(y).substring(x, x + 1).contains(character);
    }
}
