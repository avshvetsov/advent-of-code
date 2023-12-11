package org.shvetsov.day3;

import org.shvetsov.Utils;

import static org.shvetsov.Utils.isIndexExistInArray;

/**
 * <a href="https://adventofcode.com/2023/day/3">Day 3</a>
 */
public class Day3 {

    public static final String INPUT_PATH = "src/main/resources/input 3-1, 3-2.txt";

    public static void main(String[] args) {
        char[][] input = Utils.parseInputInTwoDimArray(INPUT_PATH);
        Day3 day = new Day3();
        System.out.println(day.partOneAnton(input));
        System.out.println(day.partTwoAnton(input));
    }

    public int partOneAnton(char[][] engineSchema) {
        int sum = 0;
        for (int i = 0; i < engineSchema.length; i++) {
            int start = -1;
            for (int j = 0; j < engineSchema[0].length; j++) {
                if (Character.isDigit(engineSchema[i][j])) {
                    if (start == -1) {
                        start = j;
                    }
                    if (!isIndexExistInArray(engineSchema, i, j + 1)) {
                        sum += validateNeighbours(engineSchema, i, start, j);
                    }
                } else {
                    sum += validateNeighbours(engineSchema, i, start, j - 1);
                    start = -1;
                }
            }
        }
        return sum;
    }

    private int validateNeighbours(char[][] engineSchema, int row, int startColumn, int endColumn) {
        if (startColumn == -1) {
            return 0;
        }
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = startColumn - 1; j <= endColumn + 1; j++) {
                if (isIndexExistInArray(engineSchema, i, j)) {
                    if (engineSchema[i][j] != '.' && (i != row || j > endColumn || j < startColumn)) {
                        return getIntegerFromArray(engineSchema, row, startColumn, endColumn);
                    }
                }
            }
        }
        return 0;
    }

    private int getIntegerFromArray(char[][] engineSchema, int row, int startColumn, int endColumn) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = startColumn; j <= endColumn; j++) {
            stringBuilder.append(engineSchema[row][j]);
        }
        return Integer.parseInt(stringBuilder.toString());
    }


    public int partTwoAnton(char[][] engineSchema) {
        return 0;
    }


}
