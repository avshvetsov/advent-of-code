package org.shvetsov.day4;

import org.shvetsov.utils.PointOld;
import org.shvetsov.utils.Utils;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/4">Day 4</a>
 */
public class Day4 {

    char[] xmas = new char[]{'X', 'M', 'A', 'S'};

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public long partOne(List<String> input) {
        char[][] matrix = Utils.parseInputInTwoDimArray(input);
        long result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'X') {
                    result += countXmasFrom(matrix, PointOld.of(i, j));
                }
            }
        }
        return result;
    }

    private long countXmasFrom(char[][] matrix, PointOld start) {
        long count = 0;
        for (int[] direction : directions) {
            int i = 0;
            PointOld current = PointOld.of(start.r, start.c);
            while (current.isIndexExistInArray(matrix) && i < xmas.length && matrix[current.r][current.c] == xmas[i]) {
                current.moveThis(direction);
                i++;
            }
            if (i == xmas.length) {
                count++;
            }
        }
        return count;
    }


    public long partTwo(List<String> input) {
        char[][] matrix = Utils.parseInputInTwoDimArray(input);
        long result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isXmas(matrix, i, j)) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean isXmas(char[][] matrix, int i, int j) {
        if (matrix[i][j] != 'A' || i < 1 || i > matrix.length - 2 || j < 1 || j > matrix[0].length - 2) {
            return false;
        } else {
            return ((matrix[i + 1][j + 1] == 'M' && matrix[i - 1][j - 1] == 'S') || (matrix[i + 1][j + 1] == 'S' && matrix[i - 1][j - 1] == 'M'))
                   &&
                   ((matrix[i + 1][j - 1] == 'M' && matrix[i - 1][j + 1] == 'S') || (matrix[i + 1][j - 1] == 'S' && matrix[i - 1][j + 1] == 'M'));
        }
    }

}