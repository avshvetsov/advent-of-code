package org.shvetsov.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/1">Day 1</a>
 */
public class Day1 {

    public long partOne(List<String> input) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String s : input) {
            String[] split = s.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        long result = 0;
        for (int i = 0; i < input.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }
        return result;
    }


    public long partTwo(List<String> input) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String s : input) {
            String[] split = s.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }
        Map<Integer, Long> rightMap = right.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long result = 0;
        for (Integer i : left) {
            result += rightMap.getOrDefault(i, 0L) * i;
        }
        return result;
    }

}