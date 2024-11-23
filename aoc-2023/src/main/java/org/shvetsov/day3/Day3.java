package org.shvetsov.day3;

import lombok.Data;
import org.shvetsov.utils.Utils;

import java.util.HashSet;
import java.util.Set;

import static org.shvetsov.utils.Utils.isIndexExistInArray;

/**
 * <a href="https://adventofcode.com/2023/day/3">Day 3</a>
 */
public class Day3 {

    public static final String INPUT_PATH = "src/main/resources/day3/input 3 Anton.txt";

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
        int sumGearRatio = 0;
        for (int i = 0; i < engineSchema.length; i++) {
            for (int j = 0; j < engineSchema[i].length; j++) {
                if (engineSchema[i][j] == '*') {
                    sumGearRatio += findGearRatio(engineSchema, i, j);
                }
            }
        }
        return sumGearRatio;
    }

    private int findGearRatio(char[][] engineSchema, int row, int column) {
        Set<Part> parts = new HashSet<>();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isIndexExistInArray(engineSchema, i, j)) {
                    if (Character.isDigit(engineSchema[i][j])) {
                        parts.add(new Part(engineSchema, i, j));
                    }
                }
            }
        }
        if (parts.size() > 1) {
            return parts.stream()
                    .mapToInt(Part::getValue)
                    .reduce(1, (left, right) -> left * right);
        } else return 0;
    }


    @Data
    private class Part {
        private int value;
        private int row;
        private int startColumn;
        private int endColumn;

        public Part(char[][] engineSchema, int row, int column) {
            this.row = row;
            this.startColumn = inferStartColumn(engineSchema, row, column);
            this.endColumn = inferEndColumn(engineSchema, row, column);
            this.value = getIntegerFromArray(engineSchema, this.row, this.startColumn, this.endColumn);
        }

        private int inferEndColumn(char[][] engineSchema, int row, int column) {
            do {
                if (isIndexExistInArray(engineSchema, row, column + 1)) {
                    column++;
                } else return column;
            } while (Character.isDigit(engineSchema[row][column]));
            return --column;
        }

        private int inferStartColumn(char[][] engineSchema, int row, int column) {
            do {
                if (isIndexExistInArray(engineSchema, row, column - 1)) {
                    column--;
                } else return column;
            } while (Character.isDigit(engineSchema[row][column]));
            return ++column;
        }
    }
}
