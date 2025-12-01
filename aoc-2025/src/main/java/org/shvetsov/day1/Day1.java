package org.shvetsov.day1;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2025/day/1">Day 1</a>
 */
public class Day1 {

    static final int START = 50;

    public long partOne(List<String> input) {
        int zeroCount = 0;
        int position = START;
        for (String s : input) {
            int num = Integer.parseInt(s.substring(1));
            if (s.startsWith("L")) {
                position -= num;
            } else {
                position += num;
            }
            if (position % 100 == 0) {
                zeroCount++;
            }
        }
        return zeroCount;
    }


    public long partTwo(List<String> input) {
        int zeroCount = 0;
        int position = START;
        for (String s : input) {
            int num = Integer.parseInt(s.substring(1));
            zeroCount += num / 100;
            num %= 100;
            if (s.startsWith("L")) {
                position -= num;
            } else {
                position += num;
            }
            if (position < 0) {
                if (position + num != 0) {
                    zeroCount++;
                }
                position += 100;
            } else if (position >= 100) {
                zeroCount++;
                position -= 100;
            } else if (position == 0) {
                zeroCount++;
            }
        }
        return zeroCount;
    }

}