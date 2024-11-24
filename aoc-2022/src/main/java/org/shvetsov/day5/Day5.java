package org.shvetsov.day5;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2022/day/5">Day 5</a>
 */
public class Day5 {

    public String partOne(List<String> input) {
        int emptyIndex = emptyLine(input);
        List<Stack<Character>> crates = initializeCrates(input.subList(0, emptyIndex));
        for (String action : input.subList(emptyIndex + 1, input.size())) {
            int count = Integer.parseInt(StringUtils.substringBetween(action, "move ", " from"));
            int from = Integer.parseInt(StringUtils.substringBetween(action, "from ", " to")) - 1;
            int to = Integer.parseInt(StringUtils.substringAfter(action, "to ")) - 1;
            while (count > 0) {
                crates.get(to).push(crates.get(from).pop());
                count--;
            }
        }
        return crates.stream().map(Stack::pop).map(String::valueOf).collect(Collectors.joining());
    }

    private List<Stack<Character>> initializeCrates(List<String> input) {
        List<Stack<Character>> crates = new ArrayList<>();
        int stackCount = Integer.parseInt(input.getLast().substring(input.getLast().length() - 1));
        while (stackCount > 0) {
            crates.add(new Stack<>());
            stackCount--;
        }
        for (int i = input.size() - 2; i >= 0; i--) {
            String row = input.get(i);
            int j = 1;
            int stackIndex = 0;
            while (j < row.length()) {
                if (row.charAt(j) != ' ') {
                    crates.get(stackIndex).push(row.charAt(j));
                }
                j += 4;
                stackIndex++;
            }

        }
        return crates;
    }

    private int emptyLine(List<String> input) {
        int index = 0;
        while (!input.get(index).isEmpty()) {
            index++;
        }
        return index;
    }


    public String partTwo(List<String> input) {
        int emptyIndex = emptyLine(input);
        List<Stack<Character>> crates = initializeCrates(input.subList(0, emptyIndex));
        for (String action : input.subList(emptyIndex + 1, input.size())) {
            int count = Integer.parseInt(StringUtils.substringBetween(action, "move ", " from"));
            int from = Integer.parseInt(StringUtils.substringBetween(action, "from ", " to")) - 1;
            int to = Integer.parseInt(StringUtils.substringAfter(action, "to ")) - 1;
            Stack<Character> temp = new Stack<>();
            for (int i = 0; i < count; i++) {
                temp.push(crates.get(from).pop());
            }
            for (int i = 0; i < count; i++) {
                crates.get(to).push(temp.pop());
            }
        }
        return crates.stream().map(Stack::pop).map(String::valueOf).collect(Collectors.joining());
    }

}