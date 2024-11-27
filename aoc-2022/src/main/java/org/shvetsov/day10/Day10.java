package org.shvetsov.day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2022/day/10">Day 10</a>
 */
public class Day10 {

    public long partOne(List<String> input) {
        Set<Integer> importantCycles = new HashSet<>() {{
            add(20);
            add(60);
            add(100);
            add(140);
            add(180);
            add(220);
        }};
        long result = 0;
        int x = 1;
        int i = 0;
        for (String s : input) {
            String[] split = s.split(" ");
            i++;
            if (importantCycles.contains(i)) {
                result += (long) x * i;
            }
            if (split[0].equals("addx")) {
                i++;
                if (importantCycles.contains(i)) {
                    result += (long) x * i;
                }
                x += Integer.parseInt(split[1]);
            }
        }
        return result;
    }


    public List<String> partTwo(List<String> input) {
        int n = 40;
        boolean[][] screen = new boolean[6][n];
        int x = 1;
        int i = 0;
        for (String s : input) {
            String[] split = s.split(" ");
            if (Math.abs(x - i % n) <= 1) {
                screen[i / n][i % n] = true;
            }
            i++;
            if (split[0].equals("addx")) {
                if (Math.abs(x - i % n) <= 1) {
                    screen[i / n][i % n] = true;
                }
                i++;
                x += Integer.parseInt(split[1]);
            }
        }
        List<String> result = new ArrayList<>();
        for (boolean[] row : screen) {
            StringBuilder sb = new StringBuilder();
            for (boolean b : row) {
                if (b) sb.append('#');
                else sb.append('.');
            }
            result.add(sb.toString());
        }
        return result;
    }

}