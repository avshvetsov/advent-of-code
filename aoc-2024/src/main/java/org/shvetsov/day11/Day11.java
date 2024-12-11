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
        long tics = 0;
        Map<String, Long> current = Arrays.stream(input.getFirst().split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        while (blinks > 0) {
            Map<String, Long> next = new HashMap<>();
            for (Map.Entry<String, Long> entry : current.entrySet()) {
                tics++;
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
        System.out.println("Anton tics: " + tics);
        return current.values().stream().mapToLong(l -> l).sum();
    }

    //https://github.com/Dany41/python-practicing/blob/master/adventofcode/2024/day11/day11.py
    public long partTwoDany(List<String> input, int blinks) {
        long result = 0;
        String[] line = input.getFirst().split(" ");
        for (String x : line) {
            result += recursiveBlinking(x, blinks);
        }
        System.out.println("Dany tics: " + tics);
        return result;
    }

    Map<CacheKey, Long> dp = new HashMap<>();
    private long recursiveBlinking(String val, int blinkCount) {
        CacheKey key = new CacheKey(val, blinkCount);
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        if (blinkCount == 0) {
            return 1;
        }
        long result = 0;
        for (String x : blink(val)) {
            result += recursiveBlinking(x, blinkCount - 1);
        }
        dp.put(key, result);
        return result;
    }

    long tics = 0;

    private List<String> blink(String val) {
        tics++;
        if (val.equals("0")) {
            return List.of("1");
        } else if (val.length() % 2 == 0) {
            return List.of(val.substring(0, val.length() / 2), String.valueOf(Long.parseLong(val.substring(val.length() / 2))));
        } else {
            return List.of(String.valueOf(Long.parseLong(val) * 2024));
        }
    }

    private record CacheKey(String val, int blinkCount) {

    }
}