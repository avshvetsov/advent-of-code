package org.shvetsov.day3;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/3">Day 3</a>
 */
public class Day3 {

    public long partOne(List<String> input) {
        long totalJoltage = 0;
        for (String pack : input) {
            totalJoltage += findMaxJoltage(pack, 0, 2);
        }
        return totalJoltage;
    }

    private long findMaxJoltage(String pack, int from, int batteries) {
        if (batteries == 0) {
            return 0;
        }
        char max = '0';
        int maxPos = 0;
        for (int i = from; i <= pack.length() - batteries; i++) {
            if (pack.charAt(i) > max) {
                max = pack.charAt(i);
                maxPos = i;
            }
        }
        long maxJoltage = findMaxJoltage(pack, maxPos + 1, batteries - 1);
        return Long.parseLong(max + "0".repeat(batteries - 1)) + maxJoltage;
    }

    public long partTwo(List<String> input) {
        long totalJoltage = 0;
        for (String pack : input) {
            totalJoltage += findMaxJoltage(pack, 0, 12);
        }
        return totalJoltage;
    }

}