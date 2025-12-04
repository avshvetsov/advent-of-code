package org.shvetsov.day2;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/2">Day 2</a>
 */
public class Day2 {

    public long partOne(List<String> input) {
        long sum = 0;
        for (String range : input.getFirst().split(",")) {
            String[] fromTo = range.split("-");
            String fromS = fromTo[0];
            String toS = fromTo[1];
            long to = Long.parseLong(toS);
            String startHalf;
            if (fromS.length() % 2 == 0) {
                String left = fromS.substring(0, fromS.length() / 2);
                String right = fromS.substring(fromS.length() / 2);
                startHalf = left.compareTo(right) >= 0 ? left : String.valueOf(Long.parseLong(left) + 1);
            } else {
                startHalf = "1" + "0".repeat(fromS.length() / 2);
            }
            while (Long.parseLong(startHalf + startHalf) <= to) {
                sum += Long.parseLong(startHalf + startHalf);
                startHalf = String.valueOf(Long.parseLong(startHalf) + 1);
            }

        }
        return sum;
    }

    private final static Map<Integer, Set<Integer>> divisorsMap = new HashMap<>();

    static {
        for (int i = 1; i <= 10; i++) {
            Set<Integer> divisors = new HashSet<>();
            divisors.add(1);
            for (int d = 2; d <= Math.sqrt(i); d++) {
                if (i % d == 0) {
                    divisors.add(d);
                    divisors.add(i / d);
                }
            }
            divisorsMap.put(i, divisors);
        }
    }


    public long partTwo(List<String> input) {
        Set<Long> invalidIds = new HashSet<>();
        for (String range : input.getFirst().split(",")) {
            String[] fromTo = range.split("-");
            String fromS = fromTo[0];
            String toS = fromTo[1];
            for (int i = fromS.length(); i <= toS.length(); i++) {
                String fromStr = i == fromS.length() ? fromS : "1" + "0".repeat(i - 1);
                String toStr = i == toS.length() ? toS : "9".repeat(i);
                for (Integer divisor : divisorsMap.get(i)) {
                    String part = fromStr.substring(0, divisor);
                    String full = part.repeat(i / divisor);
                    if (full.compareTo(fromStr) < 0) {
                        part = String.valueOf(Long.parseLong(part) + 1);
                        full = part.repeat(i / divisor);
                    }
                    while (Long.parseLong(full) <= Long.parseLong(toStr)) {
                        if (i > 1) {
                            invalidIds.add(Long.parseLong(full));
                        }
                        String newPart = String.valueOf(Long.parseLong(part) + 1);
                        if (newPart.length() > part.length()) {
                            break;
                        }
                        part = newPart;
                        full = part.repeat(i / divisor);
                    }
                }
            }
        }
        return invalidIds.stream().mapToLong(Long::longValue).sum();
    }

    public long partOneRegex(List<String> input) {
        String regex = "^(\\d+)\\1$";
        return findInvalidIdsSum(input, regex);
    }

    private long findInvalidIdsSum(List<String> input, String invalidRegexPattern) {
        long sum = 0;
        for (String range : input.getFirst().split(",")) {
            String[] fromTo = range.split("-");
            long from = Long.parseLong(fromTo[0]);
            long to = Long.parseLong(fromTo[1]);
            for (long i = from; i <= to; i++) {
                if (String.valueOf(i).matches(invalidRegexPattern)) {
                    sum += i;
                }
            }
        }
        return sum;
    }

    public long partTwoRegex(List<String> input) {
        String regex = "^(\\d+)\\1+$";
        return findInvalidIdsSum(input, regex);
    }

}