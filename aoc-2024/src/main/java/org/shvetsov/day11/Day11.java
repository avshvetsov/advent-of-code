package org.shvetsov.day11;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/11">Day 11</a>
 */
public class Day11 {

    public long partOne(List<String> input, int blinks) {
        List<String> current = Arrays.asList(input.getFirst().split(" "));
        while (blinks > 0) {
            List<String> next = new ArrayList<>();
            for (String stone : current) {
                if (stone.equals("0")) {
                    next.add("1");
                } else if (stone.length() % 2 == 0) {
                    next.add(stone.substring(0, stone.length() / 2));
                    next.add(String.valueOf(Long.parseLong(stone.substring(stone.length() / 2))));
                } else {
                    next.add(String.valueOf(Long.parseLong(stone) * 2024));
                }
            }
            current = next;
            blinks--;
        }
        return current.size();
    }


    public long partTwo(List<String> input, int blinks) {
        Map<String, Long> current = Arrays.stream(input.getFirst().split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        while (blinks > 0) {
            Map<String, Long> next = new HashMap<>();
            for (Map.Entry<String, Long> entry : current.entrySet()) {
                String stone = entry.getKey();
                Long count = entry.getValue();
                if (stone.equals("0")) {
                    next.merge("1", count, Long::sum);
                } else if (stone.length() % 2 == 0) {
                    next.merge(stone.substring(0, stone.length() / 2), count, Long::sum);
                    next.merge(String.valueOf(Long.parseLong(stone.substring(stone.length() / 2))), count, Long::sum);
                } else {
                    next.merge(String.valueOf(Long.parseLong(stone) * 2024), count, Long::sum);
                }
            }
            current = next;
            blinks--;
        }
        return current.values().stream().mapToLong(l -> l).sum();
    }
}