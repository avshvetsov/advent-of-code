package org.shvetsov.day12;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2023/day/12">Day 12</a>
 */
public class Day12 {


    public static final int UNFOLD_MULTIPLIER = 5;

    public static void main(String[] args) {

    }

    public long partOneAnton(List<String> inputList) {
        long res = 0L;
        List<Spring> springs = new ArrayList<>();
        inputList.forEach(s -> springs.add(new Spring(s)));
        for (Spring spring : springs) {
            long resCurrent = countPermutations(spring);
//            System.out.println("Spring: " + spring + "; Result: " + resCurrent);
            cache.clear();
            res += resCurrent;
        }
        return res;
    }


    public long partTwoAnton(List<String> inputList) {
        long res = 0L;
        List<Spring> springs = new ArrayList<>();
        inputList.forEach(s -> springs.add(new Spring(unfold(s))));
        for (Spring spring : springs) {
            long resCurrent = countPermutations(spring);
//            System.out.println("Spring: " + spring + "; Result: " + resCurrent);
            cache.clear();
            res += resCurrent;
        }
        return res;
    }

    private String unfold(String input) {
        String schema = StringUtils.substringBefore(input, " ");
        String groups = StringUtils.substringAfter(input, " ");
        List<String> schemaList = Collections.nCopies(UNFOLD_MULTIPLIER, schema);
        List<String> groupsList = Collections.nCopies(UNFOLD_MULTIPLIER, groups);
        String res = String.join("?", schemaList) + " " + String.join(",", groupsList);
        return res;
    }


    private final Map<Spring, Long> cache = new HashMap<>();

    public long countPermutations(Spring spring) {
        //Check cache
        Long result = cache.get(spring);
        if (result != null) {
            return result;
        }
        //Init schema and groups
        String schema = spring.getSpringString();
        List<Integer> groups = spring.getDamagedGroups();
        //Checking edge cases
        if (schema.length() < groups.stream().mapToInt(Integer::intValue).sum() + groups.size() - 1) {
            //if schema can't accommodate all groups with dividers - invalid
            return 0L;
        }
        if (groups.isEmpty()) {
            if (schema.chars().noneMatch(value -> value == '#')) {
                //if groups is empty and schema contains only '.'/'?' - valid
                return 1L;
            } else {
                //if groups is empty and schema contains '#' - invalid
                return 0L;
            }
        } else if (schema.chars().allMatch(value -> value == '.')) {
            //if schema contains only '.', but we have groups - invalid
            return 0L;
        }
        if (groups.size() == 1 && schema.length() == groups.getFirst()) {
            //if schema contains only last damaged group we don't need to check char after group
            if (schema.chars().allMatch(value -> value == '#' || value == '?')) {
                //if end of schema contains only '#'/'?' - valid
                return 1L;
            } else {
                //if end of schema contains only '.' - invalid
                return 0L;
            }
        }

        //Process general cases
        char firstChar = schema.charAt(0);
        if (firstChar == '.') {
            //if first char == '.', just skip it
            result = countPermutations(new Spring(schema.substring(1), groups));
        } else if (firstChar == '?') {
            //if first char == '?', then run 2 validation with '.' and with '#' at the start
            String dotSchema = '.' + schema.substring(1);
            String hashSchema = '#' + schema.substring(1);
            result = countPermutations(new Spring(dotSchema, groups)) + countPermutations(new Spring(hashSchema, groups));
        } else if (firstChar == '#') {
            //if first char == '#' we should check full group and char after group
            Integer currentGroup = groups.getFirst();
            String schemaGroup = schema.substring(0, currentGroup);
            char charAfterGroup = schema.charAt(currentGroup);
            if (schemaGroup.chars().allMatch(value -> value == '#' || value == '?') && (charAfterGroup == '.' || charAfterGroup == '?')) {
                //if group contains only '#'/'?' and char after group is '.'/'?', then skip group and proceed validation
                String newSchema = schema.substring(currentGroup + 1);
                List<Integer> newGroups = new ArrayList<>(groups);
                newGroups.removeFirst();
                result = countPermutations(new Spring(newSchema, newGroups));
            } else {
                //if group contains only '.' or char after group is '#', then it's invalid schema
                return 0L;
            }
        }
        //Save cache
        cache.put(spring, result);
        return result;
    }


    @Data
    public static class Spring {
        private final String springString;
        private final List<Integer> damagedGroups;

        public Spring(String input) {
            this.springString = StringUtils.substringBefore(input, " ");
            this.damagedGroups = Arrays.stream(StringUtils.substringAfter(input, " ").split(",")).map(Integer::valueOf).collect(Collectors.toList());
        }

        public Spring(String springString, List<Integer> damagedGroups) {
            this.springString = springString;
            this.damagedGroups = damagedGroups;
        }
    }
}