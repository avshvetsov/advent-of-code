package org.shvetsov.day19;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/19">Day 19</a>
 */
public class Day19 {

    public long partOne(List<String> input) {
        Set<String> available = Arrays.stream(input.getFirst().split(", "))
                .collect(Collectors.toSet());
        long result = 0;
        for (int i = 2; i < input.size(); i++) {
            if (canConstruct(input.get(i), available)) {
                result++;
            }
        }
        return result;
    }

    Map<String, Boolean> cache = new HashMap<>(){{put("", true);}};

    private boolean canConstruct(String s, Set<String> available) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        for (String pattern : available.stream().filter(s::startsWith).toList()) {
            if (canConstruct(s.substring(pattern.length()), available)) {
                cache.put(s, true);
                return true;
            }
        }
        cache.put(s, false);
        return false;
    }


    public long partTwo(List<String> input) {
        Set<String> available = Arrays.stream(input.getFirst().split(", "))
                .collect(Collectors.toSet());
        long result = 0;
        for (int i = 2; i < input.size(); i++) {
            result += howConstructNum(input.get(i), available);
        }
        return result;
    }

    Map<String, Long> cache2 = new HashMap<>(){{put("", 1L);}};

    private long howConstructNum(String s, Set<String> available) {
        if (cache2.containsKey(s)) {
            return cache2.get(s);
        }
        long result = 0;
        for (String pattern : available.stream().filter(s::startsWith).toList()) {
            result += howConstructNum(s.substring(pattern.length()), available);
        }
        cache2.put(s, result);
        return result;
    }

}