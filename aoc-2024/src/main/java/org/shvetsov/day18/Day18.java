package org.shvetsov.day18;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2024/day/18">Day 18</a>
 */
public class Day18 {

    public long partOne(List<String> input, int bytes, int length) {
        char[][] memory = new char[length][length];
        for (char[] row : memory) {
            Arrays.fill(row, '.');
        }
        for (int i = 0; i < bytes; i++) {
            int[] coordinate = Arrays.stream(input.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            memory[coordinate[0]][coordinate[1]] = '#';
        }
        final Point start = new Point(0, 0);
        final Point end = new Point(length - 1, length - 1);
        return shortestPath(memory, start, end);
    }

    private int shortestPath(char[][] memory, Point start, Point end) {
        Set<Point> visited = new HashSet<>();
        Set<Point> current = new HashSet<>();
        visited.add(start);
        current.add(start);
        int steps = 0;
        while (!current.contains(end)) {
            Set<Point> next = new HashSet<>();
            for (Point point : current) {
                for (Direction direction : Direction.values()) {
                    Point nextP = point.move(direction);
                    if (Utils.isIndexExistInArray(memory, nextP.r(), nextP.c()) && !visited.contains(nextP) && memory[nextP.r()][nextP.c()] != '#') {
                        next.add(nextP);
                        visited.add(nextP);
                    }
                }
            }
            current = next;
            if (current.isEmpty()) {
                return -1;
            }
            steps++;
        }
        return steps;
    }

    public String partTwo(List<String> input, int safeBytesNumber, int length) {
        final Point start = new Point(0, 0);
        final Point end = new Point(length - 1, length - 1);

        char[][] memory = new char[length][length];
        for (char[] row : memory) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < input.size(); i++) {
            int[] coordinate = Arrays.stream(input.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
            memory[coordinate[0]][coordinate[1]] = '#';
            if (i >= safeBytesNumber) {
                if (shortestPath(memory, start, end) == -1) {
                    return input.get(i);
                }
            }
        }
        throw new IllegalStateException("Path to exit never blocks with corrupted bytes");
    }

}