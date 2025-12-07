package org.shvetsov.day6;

import org.shvetsov.utils.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2025/day/6">Day 6</a>
 */
public class Day6 {

    public long partOne(List<String> input) {
        String[] signs = input.getLast().split("\\s+");
        long[] results = new long[signs.length];
        for (int i = 0; i < results.length; i++) {
            if (signs[i].equals("+")) {
                results[i] = 0;
            } else if (signs[i].equals("*")) {
                results[i] = 1;
            }
        }
        for (int i = 0; i < input.size() - 1; i++) {
            String[] current = input.get(i).trim().split("\\s+");
            for (int j = 0; j < current.length; j++) {
                if (signs[j].equals("+")) {
                    results[j] += Integer.parseInt(current[j]);
                } else if (signs[j].equals("*")) {
                    results[j] *= Integer.parseInt(current[j]);
                }
            }
        }
        return Arrays.stream(results).sum();
    }

    public long partTwo(List<String> input) {
        char[][] inputArray = Utils.parseInputInTwoDimArray(input);
        int r = inputArray.length;
        long result = 0;
        char sign = '-';
        long problemSolution = 0;
        for (int j = 0; j < inputArray[0].length; j++) {
            if (isColumnContainsOnlySpaces(inputArray, j)) {
                result += problemSolution;
                continue;
            }
            if (inputArray[r - 1][j] != ' ' && inputArray[r - 1][j] != '\u0000') {
                sign = inputArray[r - 1][j];
                problemSolution = sign == '+' ? 0 : 1;
            }
            int i = 0;
            int num = 0;
            while (i < r - 1) {
                if (Character.isDigit(inputArray[i][j])) {
                    num *= 10;
                    num += inputArray[i][j] - '0';
                }
                i++;
            }
            if (sign == '+') {
                problemSolution += num;
            } else if (sign == '*') {
                problemSolution *= num;
            }
        }
        result += problemSolution;
        return result;
    }

    private boolean isColumnContainsOnlySpaces(char[][] inputArray, int j) {
        for (char[] chars : inputArray) {
            if (chars[j] != ' ') {
                return false;
            }
        }
        return true;
    }

}
