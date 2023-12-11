package org.shvetsov.day2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *<a href="https://adventofcode.com/2023/day/2">Day 2</a>
 */
public class Day2Dany {

    public static final String INPUT = "input 2-1, 2-2.txt";


    private static final Map<String, Integer> part1Limits = Map.of("red", 12, "green", 13, "blue", 14);

    public static int part1Dany(String[] input) {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            int gameNumber = i + 1;
            String[] gameContent = str.substring(str.indexOf(": ") + 2).split("; ");
            boolean isGameValid = true;
            for (int j = 0; j < gameContent.length; j++) {
                isGameValid = isGameValid && Arrays.stream(gameContent[j].split(", "))
                        .allMatch(cubes -> {
                            String[] numberAndColor = cubes.split(" ");
                            return part1Limits.get(numberAndColor[1]) >= Integer.parseInt(numberAndColor[0]);
                        });
            }
            if (isGameValid) result += gameNumber;
        }
        return result;
    }

    public static int part2Dany(String[] input) {
        int result = 0;
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            String[] gameContent = str.substring(str.indexOf(": ") + 2).split("; ");
            Map<String, Integer> minCubes = new HashMap<>(Map.of("red", 1, "green", 1, "blue", 1));
            for (int j = 0; j < gameContent.length; j++) {
                String[] cubesInfo = gameContent[j].split(", ");
                for (int k = 0; k < cubesInfo.length; k++) {
                    String[] numberAndColor = cubesInfo[k].split(" ");
                    minCubes.merge(numberAndColor[1], Integer.parseInt(numberAndColor[0]), Math::max);
                }
            }
            result += minCubes.values().stream().reduce(1, (a, b) -> a * b);
        }
        return result;
    }


}
