package org.shvetsov.day2;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2025/day/2">Day 2</a>
 */
public class Day2 {

    public long partOne(List<String> input) {
        long sum = 0;
        for (String range : input.getFirst().split(",")) {
            String[] fromTo = range.split("-");
            String fromS = fromTo[0];
            String toS = fromTo[1];
            long to = Long.parseLong(toS);
            String startHalf;
            if (fromS.length() % 2 == 0) {
                String left = fromS.substring(0, fromS.length() / 2);
                String right = fromS.substring(fromS.length() / 2);
                startHalf = left.compareTo(right) >= 0 ? left : String.valueOf(Long.parseLong(left) + 1);
            } else {
                startHalf = "1" + "0".repeat(fromS.length() / 2);
            }
            while (Long.parseLong(startHalf + startHalf) <= to) {
                sum += Long.parseLong(startHalf + startHalf);
                startHalf = String.valueOf(Long.parseLong(startHalf) + 1);
            }

        }
        return sum;
    }


    public long partTwo(List<String> input) {

        return -1;
    }

}