package org.shvetsov.day1;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2022/day/1">Day 1</a>
 */
public class Day1 {

    public long partOne(List<String> input) {
        long result = 0;
        long current = 0;
        for (String s : input) {
            if (s.isEmpty()) {
                result = Math.max(result, current);
                current = 0;
            } else {
                current += Long.parseLong(s);
            }
        }
        result = Math.max(result, current);
        return result;
    }


    public long partTwo(List<String> input) {
        long current = 0;
        List<Long> sums = new ArrayList<>();
        for (String s : input) {
            if (s.isEmpty()) {
                sums.add(current);
                current = 0;
            } else {
                current += Long.parseLong(s);
            }
        }
        sums.add(current);
        return sums.stream().sorted(Comparator.reverseOrder()).limit(3).mapToLong(value -> value).sum();
    }

}