package org.shvetsov.day4;

import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://adventofcode.com/2025/day/4">Day 4</a>
 */
public class Day4 {

    public long partOne(List<String> input) {
        int accessible = 0;
        char[][] diagram = Utils.parseInputInTwoDimArray(input);
        int[][] neighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int r = 0; r < diagram.length; r++) {
            for (int c = 0; c < diagram[r].length; c++) {
                if (diagram[r][c] == '@') {
                    int neighborsCount = 0;
                    for (int[] neighbor : neighbors) {
                        int i = r + neighbor[0];
                        int j = c + neighbor[1];
                        if (Utils.isIndexExistInArray(diagram, i, j) && diagram[i][j] == '@') {
                            neighborsCount++;
                        }
                    }
                    if (neighborsCount < 4) {
                        accessible++;
                    }
                }
            }
        }
        return accessible;
    }

    public long partTwo(List<String> input) {
        char[][] diagram = Utils.parseInputInTwoDimArray(input);
        int[][] neighborsCountMap = new int[diagram.length][diagram[0].length];
        int[][] neighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Queue<Point> accessibleQueue = new LinkedList<>();
        for (int r = 0; r < diagram.length; r++) {
            for (int c = 0; c < diagram[r].length; c++) {
                if (diagram[r][c] == '@') {
                    int neighborsCount = 0;
                    for (int[] neighbor : neighbors) {
                        int i = r + neighbor[0];
                        int j = c + neighbor[1];
                        if (Utils.isIndexExistInArray(diagram, i, j) && diagram[i][j] == '@') {
                            neighborsCount++;
                        }
                    }
                    neighborsCountMap[r][c] = neighborsCount + 1;
                    if (neighborsCount < 4) {
                        accessibleQueue.add(new Point(r, c));
                    }
                }
            }
        }
        int accessible = 0;
        while (!accessibleQueue.isEmpty()) {
            Point current = accessibleQueue.poll();
            for (Point neighbor : current.neighbors(diagram, true)) {
                if (neighborsCountMap[neighbor.r()][neighbor.c()] > 0) {
                    neighborsCountMap[neighbor.r()][neighbor.c()]--;
                    if (neighborsCountMap[neighbor.r()][neighbor.c()] == 4) {
                        accessibleQueue.add(neighbor);
                    }
                }
            }
            neighborsCountMap[current.r()][current.c()] = 0;
            accessible++;
        }
        return accessible;
    }

}
