package org.shvetsov.day11;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/11">Day 11</a>
 */
public class Day11 {

    public long partOne(List<String> input) {
        Map<String, List<String>> devices = new HashMap<>();
        for (String s : input) {
            String in = StringUtils.substringBefore(s, ":");
            List<String> out = Arrays.stream(StringUtils.substringAfter(s, ": ").split(" ")).toList();
            devices.put(in, out);
        }
        return countAllRouts(devices, "you", "out");
    }

    private final Map<Pair<String, String>, Long> cache = new HashMap<>();

    private long countAllRouts(Map<String, List<String>> devices, String current, String end) {
        if (cache.containsKey(Pair.of(current, end))) {
            return cache.get(Pair.of(current, end));
        }
        if (current.equals(end)) {
            return 1;
        }
        long routs = 0;
        List<String> outs = devices.get(current);
        if (outs == null) {
            return 0;
        }
        for (String out : outs) {
            routs += countAllRouts(devices, out, end);
        }
        cache.put(Pair.of(current, end), routs);
        return routs;
    }

    public long partTwo(List<String> input) {
        Map<String, List<String>> devices = new HashMap<>();
        for (String s : input) {
            String in = StringUtils.substringBefore(s, ":");
            List<String> out = Arrays.stream(StringUtils.substringAfter(s, ": ").split(" ")).toList();
            devices.put(in, out);
        }
        return countAllRouts(devices, "svr", "dac") * countAllRouts(devices, "dac", "fft") * countAllRouts(devices, "fft", "out")
               + countAllRouts(devices, "svr", "fft") * countAllRouts(devices, "fft", "dac") * countAllRouts(devices, "dac", "out");
    }

}
