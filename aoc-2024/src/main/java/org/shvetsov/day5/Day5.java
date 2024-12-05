package org.shvetsov.day5;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2024/day/5">Day 5</a>
 */
public class Day5 {

    public long partOne(List<String> input) {
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        int i = 0;
        String curr = input.get(i);
        while (!curr.isEmpty()) {
            String[] split = curr.split("\\|");
            rules.computeIfAbsent(Integer.parseInt(split[0]), k -> new HashSet<>()).add(Integer.parseInt(split[1]));
            i++;
            curr = input.get(i);
        }
        i++;
        int result = 0;
        while (i < input.size()) {
            curr = input.get(i);
            int[] update = Arrays.stream(curr.split(",")).mapToInt(Integer::parseInt).toArray();
            if (isValidUpdate(update, rules)) {
                result += update[update.length / 2];
            }
            i++;
        }
        return result;
    }

    private boolean isValidUpdate(int[] update, Map<Integer, Set<Integer>> rules) {
        for (int i = 1; i < update.length; i++) {
            Set<Integer> less = rules.getOrDefault(update[i], new HashSet<>());
            for (int j = i - 1; j >= 0; j--) {
                if(less.contains(update[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public long partOneWithSorting(List<String> input) {
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        int i = 0;
        String ruleString = input.get(i);
        while (!ruleString.isEmpty()) {
            String[] split = ruleString.split("\\|");
            rules.computeIfAbsent(Integer.parseInt(split[0]), k -> new HashSet<>()).add(Integer.parseInt(split[1]));
            rules.computeIfAbsent(Integer.parseInt(split[1]), k -> new HashSet<>());
            i++;
            ruleString = input.get(i);
        }
        i++;    //skip empty line
        int result = 0;
        while (i < input.size()) {
            String updateString = input.get(i);
            List<Integer> update = Arrays.stream(updateString.split(",")).map(Integer::parseInt).toList();
            List<Integer> sortedUpdate = sort(update, rules);
            if (update.equals(sortedUpdate)) {
                result += sortedUpdate.get(sortedUpdate.size() / 2);
            }
            i++;
        }
        return result;
    }


    public long partTwo(List<String> input) {
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        int i = 0;
        String ruleString = input.get(i);
        while (!ruleString.isEmpty()) {
            String[] split = ruleString.split("\\|");
            rules.computeIfAbsent(Integer.parseInt(split[0]), k -> new HashSet<>()).add(Integer.parseInt(split[1]));
            rules.computeIfAbsent(Integer.parseInt(split[1]), k -> new HashSet<>());
            i++;
            ruleString = input.get(i);
        }
        i++;    //skip empty line
        int result = 0;
        while (i < input.size()) {
            String updateString = input.get(i);
            List<Integer> update = Arrays.stream(updateString.split(",")).map(Integer::parseInt).toList();
            List<Integer> sortedUpdate = sort(update, rules);
            if (!update.equals(sortedUpdate)) {
                result += sortedUpdate.get(sortedUpdate.size() / 2);
            }
            i++;
        }
        return result;
    }

    //commented is mo space efficient way but less time efficient
    //if numbers has no bounds (100 in our examole) - use commented version
    private List<Integer> sort(List<Integer> update, Map<Integer, Set<Integer>> rules) {
        List<Integer> sorted = new ArrayList<>(update);
//        int[] updateRuleCount = new int[update.size()];
        int[] updateRuleCount = new int[100];
        for (int i = 0; i < update.size(); i++) {
            for (int j = 0; j < update.size(); j++) {
                if (rules.get(update.get(i)).contains(update.get(j))) {
//                    updateRuleCount[i]++;
                    updateRuleCount[update.get(i)]++;
                }
            }
        }
//        sorted.sort(Comparator.comparing(value -> updateRuleCount[update.indexOf(value)], Comparator.reverseOrder()));
        sorted.sort(Comparator.comparing(value -> updateRuleCount[value], Comparator.reverseOrder()));
        return sorted;
    }

}