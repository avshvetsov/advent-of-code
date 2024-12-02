package org.shvetsov.day2;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/2">Day 2</a>
 */
public class Day2 {

    public long partOne(List<String> input) {
        int result = 0;
        for (String s : input) {
            int[] report = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean isSafe = isSafe(report);
            if (isSafe) {
                result++;
            }
        }
        return result;
    }

    private boolean isSafe(int[] report) {
        boolean isSafe = true;
        int prev = report[0];
        boolean asc = report[1] > prev;
        for (int i = 1; i < report.length; i++) {
            int diff = report[i] - prev;
            if (diff > 0 != asc || Math.abs(diff) > 3 || Math.abs(diff) == 0) {
                isSafe = false;
                break;
            }
            prev = report[i];
        }
        return isSafe;
    }


    public long partTwo(List<String> input) {
        int result = 0;
        for (String s : input) {
            int[] report = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            if (isSafe(report)) {
                result++;
            } else {
                for (int i = 0; i < report.length; i++) {
                    int[] newReport = new int[report.length - 1];
                    System.arraycopy(report, 0, newReport, 0, i);
                    System.arraycopy(report, i + 1, newReport, i, report.length - i - 1);
                    if (isSafe(newReport)) {
                        result++;
                        break;
                    }
                }
            }
        }
        return result;
    }

}