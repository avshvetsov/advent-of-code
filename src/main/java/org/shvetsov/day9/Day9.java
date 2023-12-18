package org.shvetsov.day9;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/9">Day 9</a>
 */
public class Day9 {

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> historyString) {
        List<List<Integer>> history = new ArrayList<>();
        for (String s :
                historyString) {
            history.add(Arrays.stream(s.split(" "))
                    .map(Integer::parseInt)
                    .toList());
        }
        return history.stream()
                .mapToInt(this::predictFuture)
                .sum();
    }

    private int predictFuture(List<Integer> input) {
        if (input.stream().allMatch(integer -> integer == 0)) {
            return 0;
        }
        List<Integer> diffList = IntStream.range(0, input.size() - 1)
                .mapToObj(value -> input.get(value + 1) - input.get(value))
                .toList();

        return input.getLast() + predictFuture(diffList);
    }

    public int partTwoAnton(List<String> historyString) {
        List<List<Integer>> history = new ArrayList<>();
        for (String s :
                historyString) {
            history.add(Arrays.stream(s.split(" "))
                    .map(Integer::parseInt)
                    .toList());
        }
        return history.stream()
                .mapToInt(this::predictPast)
                .sum();
    }

    private int predictPast(List<Integer> input) {
        if (input.stream().allMatch(integer -> integer == 0)) {
            return 0;
        }
        List<Integer> diffList = IntStream.range(0, input.size() - 1)
                .mapToObj(value -> input.get(value + 1) - input.get(value))
                .toList();
        return input.getFirst() - predictPast(diffList);
    }


}

