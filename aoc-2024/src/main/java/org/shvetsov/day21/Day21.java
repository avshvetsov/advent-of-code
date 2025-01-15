package org.shvetsov.day21;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2024/day/21">Day 21</a>
 */
public class Day21 {

    Map<String, List<String>> numericMap = new HashMap<>() {{
        put("A0", List.of("<A"));
        put("02", List.of("^A"));
        put("29", List.of(">^^A", "^>^A", "^^>A"));
        put("9A", List.of("vvvA"));
        put("A9", List.of("^^^A"));
        put("98", List.of("<A"));
        put("80", List.of("vvvA"));
        put("0A", List.of(">A"));
        put("A1", List.of("^<<A", "<^<A"));
        put("17", List.of("^^A"));
        put("79", List.of(">>A"));
        put("A4", List.of("^^<<A", "^<^<A", "^<<^A", "<^^<A", "<^<^A"));
        put("45", List.of(">A"));
        put("56", List.of(">A"));
        put("6A", List.of("vvA"));
        put("A3", List.of("^A"));
        put("37", List.of("^^<<A", "^<^<A", "^<<^A", "<^^<A", "<^<^A", "<<^^A"));

        put("34", List.of("^<<A", "<^<A", "<<^A"));
        put("40", List.of(">vvA", "v>vA"));
        put("A5", List.of("^^<A", "^<^A", "<^^A"));
        put("58", List.of("^A"));
        put("86", List.of(">vA", "v>A"));
        put("A8", List.of("^^^<A", "^^<^A", "^<^^A", "<^^^A"));
        put("83", List.of(">vvA", "v>vA", "vv>A"));
        put("39", List.of("^^A"));
        put("41", List.of("vA"));
        put("13", List.of(">>A"));
        put("3A", List.of("vA"));
        put("96", List.of("vA"));
        put("68", List.of("^<A", "<^A"));
        put("8A", List.of("vvv>A", "vv>vA", "v>vvA", ">vvvA"));
    }};

    Map<String, List<String>> directionalMap = new HashMap<>() {{
        put("A^", List.of("<A"));
        put("A>", List.of("vA"));
        put("Av", List.of("<vA", "v<A"));
        put("A<", List.of("<v<A", "v<<A"));
        put("^>", List.of(">vA", "v>A"));
        put("^<", List.of("v<A"));
        put("^A", List.of(">A"));
        put(">v", List.of("<A"));
        put(">^", List.of("<^A", "^<A"));
        put(">A", List.of("^A"));
        put("v<", List.of("<A"));
        put("v>", List.of(">A"));
        put("vA", List.of(">^A", "^>A"));
        put("<^", List.of(">^A"));
        put("<v", List.of(">A"));
        put("<A", List.of(">^>A", ">>^A"));
        put("AA", List.of("A"));
        put("^^", List.of("A"));
        put(">>", List.of("A"));
        put("vv", List.of("A"));
        put("<<", List.of("A"));
    }};

    Map<CacheKey, Long> cache = new HashMap<>();

    record CacheKey(List<String> dirSeqs, int dirPadNum) {
    }

    public long partOne(List<String> input, int dirPadNum) {
        long result = 0;
        for (String code : input) {
            long length = 0;
            char from = 'A';
            for (char to : code.toCharArray()) {
                length += findShortestDirectionalSequence(numericMap.get(String.valueOf(from) + to), dirPadNum);
                from = to;
            }
            result += length * Integer.parseInt(code.substring(0, 3));
        }
        return result;
    }

    private long findShortestDirectionalSequence(List<String> dirSeqs, int dirPadNum) {
        if (dirPadNum == 0) {
            return dirSeqs.stream().min(Comparator.comparingInt(String::length))
                    .orElseThrow(() -> new RuntimeException("No shortest sequence found"))
                    .length();
        }
        CacheKey key = new CacheKey(dirSeqs, dirPadNum);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        long result = Long.MAX_VALUE;
        for (String dirSeq : dirSeqs) {
            long length = 0;
            char from = 'A';
            for (char to : dirSeq.toCharArray()) {
                length += findShortestDirectionalSequence(directionalMap.get(String.valueOf(from) + to), dirPadNum - 1);
                from = to;
            }
            result = Math.min(result, length);
        }
        cache.put(key, result);
        return result;
    }


    public long partTwo(List<String> input, int dirPadNum) {
        long result = 0;
        for (String code : input) {
            long length = 0;
            char from = 'A';
            for (char to : code.toCharArray()) {
                length += findShortestDirectionalSequence(numericMap.get(String.valueOf(from) + to), dirPadNum);
                from = to;
            }
            result += length * Integer.parseInt(code.substring(0, 3));
        }
        return result;
    }

}