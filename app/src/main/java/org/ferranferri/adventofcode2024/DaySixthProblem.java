package org.ferranferri.adventofcode2024;

import java.util.List;

public class DaySixthProblem extends ProblemSolver{
    private ZoneMap zoneMap;

    enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    public DaySixthProblem(String inputFile) {
        super(inputFile);
    }

    static class Cell {
        boolean visited;
        Direction direction;
        boolean obstacle;
        public Cell(boolean obstacle) {
            this.visited = false;
            direction = Direction.NONE;
            this.obstacle = obstacle;
        }
    }

    static class Guard {
        public boolean inALoop = false;
        private int positionX, positionY;

        public int getPositionX() {
            return positionX;
        }

        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }

        public void changeDirection() {
            switch (direction) {
                case UP:
                    direction = Direction.RIGHT;
                    break;
                case RIGHT:
                    direction = Direction.DOWN;
                    break;
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    break;
            }
        }


        Direction direction;

    }
    static class ZoneMap {
        private final Cell[][] cells;
        private Guard guard;

        // Populating map and setting guard initial position
        public ZoneMap(int width, int height, List<String> info ) {
            cells = new Cell[width][height];
            for (int i = 0; i < width; i++) {
                String line = info.get(i);
                for (int j = 0; j < height; j++) {
                    switch (line.charAt(j)) {
                        case '.':
                            cells[j][i] = new Cell(false);
                            break;
                        case '#':
                            cells[j][i] = new Cell(true);
                            break;
                        case '^':
                            guard = new Guard();
                            guard.direction = Direction.UP;
                            guard.positionX = j;
                            guard.positionY = i;
                            cells[j][i] = new Cell(false);
                            cells[j][i].obstacle = false;
                            cells[j][i].visited = true;
                    }
                }
            }

        }

        public boolean guardNextStep() {
            int nextPositionX = 0;
            int nextPositionY = 0;
            switch (guard.direction) {
                case UP:
                    nextPositionX = guard.getPositionX();
                    nextPositionY = guard.getPositionY() - 1;
                    break;
                case RIGHT:
                    nextPositionX = guard.getPositionX() + 1;
                    nextPositionY = guard.getPositionY();
                    break;
                case DOWN:
                    nextPositionY = guard.getPositionY() + 1;
                    nextPositionX = guard.getPositionX();
                    break;
                case LEFT:
                    nextPositionX = guard.getPositionX() - 1;
                    nextPositionY = guard.getPositionY();
                    break;
            }

            if (nextPositionY < 0 || nextPositionY >= cells.length) {
                return false;
            }
            if (nextPositionX < 0 || nextPositionX >= cells[nextPositionY].length) {
                return false;
            }
            if (cells[nextPositionX][nextPositionY].obstacle) {
                guard.changeDirection();
            } else {
                // if you find that the current direction of the guard is the same that a visited position then the guard is in a loop
                if (cells[nextPositionX][nextPositionY].visited && guard.direction == cells[nextPositionX][nextPositionY].direction) {
                    guard.inALoop = true;
                }
                cells[nextPositionX][nextPositionY].visited = true;
                cells[nextPositionX][nextPositionY].direction = guard.direction;
                guard.setPositionX(nextPositionX);
                guard.setPositionY(nextPositionY);
            }
            return true;
        }

        public int countVisitedPlaces() {
            int count = 0;
            for (Cell[] cell : cells) {
                for (Cell value : cell) {
                    if (value.visited) {
                        count++;
                    }
                }
            }
            return count;
        }

        public Guard getGuard() {
            return guard;
        }
    }
    public int solveProblemDaySixthPartOne() {
        composeMap(lines);
        boolean guardInTheZone = true;
        while (guardInTheZone) {
            guardInTheZone = zoneMap.guardNextStep();
        }
        return zoneMap.countVisitedPlaces();

    }
    public int solveProblemDaySixthPartTwo() {
        composeMap(lines);
        int countLoops = 0;
        int posX = zoneMap.guard.positionX;
        int posY = zoneMap.guard.positionY;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (posX != i || posY != j) {
                    // reset map, cell properties and guard position
                    composeMap(lines);
                    // change a cell as obstacle
                    zoneMap.cells[i][j].obstacle = true;
                }
                if (loopFound()) {
                    countLoops++;
                }
            }
        }
        return countLoops;
    }

    private boolean loopFound() {
        boolean guardInTheZone = true;
        while (guardInTheZone) {
            if (zoneMap.getGuard().inALoop) {
                return true;
            }
            guardInTheZone = zoneMap.guardNextStep();
        }
        return false;
    }

    private void composeMap(List<String> lines) {
        zoneMap = new ZoneMap(lines.getFirst().length(), lines.size(), lines);

    }
}
