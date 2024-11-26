package org.shvetsov.day8;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/8">Day 8</a>
 */
public class Day8 {

    public long partOne(List<String> input) {
        int[][] forest = buildForest(input);
        int r = forest.length;
        int c = forest[0].length;
        boolean[][] visibility = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            int j = 0;
            int max = -1;
            while (max < 9 && j < c) {
                if (!visibility[i][j] && forest[i][j] > max) {
                    visibility[i][j] = true;
                }
                max = Math.max(max, forest[i][j]);
                j++;
            }
        }
        for (int i = 0; i < r; i++) {
            int j = c - 1;
            int max = -1;
            while (max < 9 && j >= 0) {
                if (!visibility[i][j] && forest[i][j] > max) {
                    visibility[i][j] = true;
                }
                max = Math.max(max, forest[i][j]);
                j--;
            }
        }
        for (int j = 0; j < c; j++) {
            int i = 0;
            int max = -1;
            while (max < 9 && i < r) {
                if (!visibility[i][j] && forest[i][j] > max) {
                    visibility[i][j] = true;
                }
                max = Math.max(max, forest[i][j]);
                i++;
            }
        }
        for (int j = 0; j < c; j++) {
            int i = r - 1;
            int max = -1;
            while (max < 9 && i >= 0) {
                if (!visibility[i][j] && forest[i][j] > max) {
                    visibility[i][j] = true;
                }
                max = Math.max(max, forest[i][j]);
                i--;
            }
        }
        long result = 0;
        for (boolean[] row : visibility) {
            for (boolean t : row) {
                if (t) result++;
            }
        }
        return result;
    }


    private int[][] buildForest(List<String> input) {
        int r = input.size();
        int c = input.getFirst().length();
        int[][] forest = new int[r][c];
        for (int i = 0; i < r; i++) {
            char[] chars = input.get(i).toCharArray();
            for (int j = 0; j < c; j++) {
                forest[i][j] = chars[j] - '0';
            }
        }
        return forest;
    }

    public long partTwo(List<String> input) {
        int[][] forest = buildForest(input);
        long result = 0;
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[i].length; j++) {
                result = Math.max(result, productOfView(forest, i, j));
            }
        }
        return result;
    }

    private long productOfView(int[][] forest, int i, int j) {
        long result = 1;
        for (Direction direction : Direction.values()) {
            int view = 0;
            Point current = Point.of(i, j);
            current.moveThis(direction);
            while (current.isIndexExistInArray(forest)) {
                view++;
                if (forest[current.r][current.c] >= forest[i][j]) {
                    break;
                }
                current.moveThis(direction);
            }
            result *= view;
        }
        return result;
    }

}