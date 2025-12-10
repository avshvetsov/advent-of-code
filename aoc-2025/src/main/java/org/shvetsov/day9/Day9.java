package org.shvetsov.day9;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/9">Day 9</a>
 */
public class Day9 {

    public long partOne(List<String> input) {
        long maxArea = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] tile1 = input.get(i).split(",");
            int x1 = Integer.parseInt(tile1[0]);
            int y2 = Integer.parseInt(tile1[1]);
            for (int j = i + 1; j < input.size(); j++) {
                String[] tile2 = input.get(j).split(",");
                int x2 = Integer.parseInt(tile2[0]);
                int y1 = Integer.parseInt(tile2[1]);
                maxArea = Math.max(maxArea, (long) (Math.abs(x1 - x2) + 1) * (Math.abs(y1 - y2) + 1));
            }
        }
        return maxArea;
    }

    public long partTwo(List<String> input) {
        long maxArea = 0;

        return maxArea;
    }

}
