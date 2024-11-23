package org.shvetsov.day2;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/2">Day 2</a>
 */
public class Day2 {

    public long partOne(List<String> input) {
        long result = 0;
        for (String s : input) {
            String[] game = s.split(" ");
            result += Shape.of(game[1]).play(Shape.of(game[0]));
        }
        return result;
    }


    public long partTwo(List<String> input) {
        long result = 0;
        for (String s : input) {
            String[] game = s.split(" ");
            result += Result.of(game[1]).play(Shape.of(game[0]));
        }
        return result;
    }

}