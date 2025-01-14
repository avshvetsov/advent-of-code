package org.shvetsov.day20;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/20">Day 20</a>
 */
public class Day20 {

    public long partOne(List<String> input, int min) {
        ParseInputResult parseInputResult = parseInputResult(input);
        long result = 0;
        List<Point> current = new ArrayList<>();
        current.add(parseInputResult.start());
        while (!current.isEmpty()) {
            for (Point currentP : current) {
                List<Point> next = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    Point nextP = currentP.move(dir);
                    if (parseInputResult.map()[nextP.r()][nextP.c()] > parseInputResult.map()[currentP.r()][currentP.c()]) {
                        next.add(nextP);
                    } else if (parseInputResult.map()[nextP.r()][nextP.c()] == Integer.MIN_VALUE) {
                        result += countCheatWays(parseInputResult.map(), parseInputResult.map()[currentP.r()][currentP.c()], nextP, min);
                    }
                }
                current = next;
            }
        }
        return result;
    }

    private ParseInputResult parseInputResult(List<String> input) {
        int[][] map = new int[input.size()][input.getFirst().length()];
        Point start = null;
        for (int i = 0; i < input.size(); i++) {
            char[] charArray = input.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                switch (c) {
                    case 'S' -> {
                        map[i][j] = 0;
                        start = new Point(i, j);
                    }
                    case '#' -> map[i][j] = Integer.MIN_VALUE;
                    default -> map[i][j] = -1;
                }
            }
        }
        List<Point> current = new ArrayList<>();
        current.add(start);
        while (!current.isEmpty()) {
            for (Point currentP : current) {
                List<Point> next = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    Point nextP = currentP.move(dir);
                    if (map[nextP.r()][nextP.c()] == -1) {
                        next.add(nextP);
                        map[nextP.r()][nextP.c()] = map[currentP.r()][currentP.c()] + 1;
                    }
                }
                current = next;
            }
        }
        return new ParseInputResult(map, start);
    }

    private long countCheatWays(int[][] map, int currentStep, Point wall, int min) {
        long result = 0;
        for (Direction dir : Direction.values()) {
            Point target = wall.move(dir);
            if (Utils.isIndexExistInArray(map, target.r(), target.c())) {
                int targetStep = map[target.r()][target.c()];
                if (targetStep > currentStep && targetStep - currentStep - 2 >= min) {
                    result++;
                }
            }
        }
        return result;
    }


    public long partOneGeneral(List<String> input, int min, int maxCheatTime) {
        ParseInputResult parseInputResult = parseInputResult(input);
        long result = 0;
        List<Point> current = new ArrayList<>();
        current.add(parseInputResult.start());
        while (!current.isEmpty()) {
            for (Point currentP : current) {
                result += countCheatWaysGeneral(parseInputResult.map(), currentP, min, maxCheatTime);
                List<Point> next = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    Point nextP = currentP.move(dir);
                    if (parseInputResult.map()[nextP.r()][nextP.c()] > parseInputResult.map()[currentP.r()][currentP.c()]) {
                        next.add(nextP);
                    }
                }
                current = next;
            }
        }
        return result;
    }

    public long partTwo(List<String> input, int minSave, int maxCheatTime) {
        ParseInputResult parseInputResult = parseInputResult(input);
        long result = 0;
        List<Point> current = new ArrayList<>();
        current.add(parseInputResult.start());
        while (!current.isEmpty()) {
            for (Point currentP : current) {
                result += countCheatWaysGeneral(parseInputResult.map(), currentP, minSave, maxCheatTime);
                List<Point> next = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    Point nextP = currentP.move(dir);
                    if (parseInputResult.map()[nextP.r()][nextP.c()] > parseInputResult.map()[currentP.r()][currentP.c()]) {
                        next.add(nextP);
                    }
                }
                current = next;
            }
        }
        return result;
    }

    private long countCheatWaysGeneral(int[][] map, Point current, int minSave, int maxCheatTime) {
        long result = 0;
        int currentStep = map[current.r()][current.c()];
        for (int i = -maxCheatTime; i <= maxCheatTime; i++) {
            for (int j = -(maxCheatTime - Math.abs(i)); j <= maxCheatTime - Math.abs(i); j++) {
                Point target = current.add(new Point(i, j));
                int distance = current.distance(target);
                if (Utils.isIndexExistInArray(map, target.r(), target.c()) &&
                    map[target.r()][target.c()] > currentStep &&
                    map[target.r()][target.c()] - currentStep - distance >= minSave) {
                    result++;
                }
            }
        }
        return result;
    }

    private record ParseInputResult(int[][] map, Point start) {
    }

}