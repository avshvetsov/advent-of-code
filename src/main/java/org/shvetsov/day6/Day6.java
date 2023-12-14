package org.shvetsov.day6;

import org.apache.commons.lang3.StringUtils;
import org.shvetsov.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/6">Day 6</a>
 */
public class Day6 {

    public static final String INPUT_PATH = "src/main/resources/input 6-1, 6-2.txt";
    public static final String INPUT_NAME = "input 6-1, 6-2.txt";

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine(INPUT_PATH);
        Day6 day = new Day6();
        System.out.println(day.partOneAnton(input));
        System.out.println(day.partTwoAnton(input));
    }

    public int partOneAnton(List<String> racesString) {
        int[] times = Arrays.stream(StringUtils.substringAfter(racesString.get(0), ":").trim().split(" +")).mapToInt(Integer::parseInt).toArray();
        int[] distances = Arrays.stream(StringUtils.substringAfter(racesString.get(1), ":").trim().split(" +")).mapToInt(Integer::parseInt).toArray();
        return IntStream.range(0, times.length)
                .map(i -> times[i] + 1 - ((int) (((double) times[i] - Math.sqrt(times[i] * times[i] - 4 * distances[i])) / 2) + 1) * 2)
                .reduce((left, right) -> left * right)
                .orElse(0);
    }

    public long partTwoAnton(List<String> racesString) {
        long time = Long.parseLong(StringUtils.substringAfter(racesString.get(0), ":").replaceAll(" +", ""));
        long distance = Long.parseLong(StringUtils.substringAfter(racesString.get(1), ":").replaceAll(" +", ""));
        long result = time + 1 - ((long) (((double) time - Math.sqrt(time * time - 4 * distance)) / 2) + 1) * 2;
        return result;
    }
}
