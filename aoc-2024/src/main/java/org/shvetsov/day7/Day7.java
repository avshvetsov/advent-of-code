package org.shvetsov.day7;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2024/day/7">Day 7</a>
 */
public class Day7 {

    public long partOne(List<String> input) {
        long sum = 0;
        for (String s : input) {
            long target = Long.parseLong(StringUtils.substringBefore(s, ":"));
            long[] args = Arrays.stream(StringUtils.substringAfter(s, ": ").split(" ")).mapToLong(Long::parseLong).toArray();
            Set<Long> current = new HashSet<>();
            current.add(args[0]);
            for (int i = 1; i < args.length; i++) {
                long arg = args[i];
                Set<Long> next = new HashSet<>();
                for (long l : current) {
                    for (OperationPart1 op : OperationPart1.values()) {
                        long opRes = op.calculate(l, arg);
                        if (opRes <= target) {
                            next.add(opRes);
                        }
                    }
                }
                current = next;
            }
            if (current.contains(target)) {
                sum += target;
            }
        }
        return sum;
    }

    public long partOneRecursion(List<String> input) {
        long sum = 0;
        for (String s : input) {
            long res = Long.parseLong(StringUtils.substringBefore(s, ":"));
            long[][] args = new long[][]{Arrays.stream(StringUtils.substringAfter(s, ":").trim().split(" ")).mapToLong(Integer::parseInt).toArray()};
            Set<Long> possibleRes = calculateRes(args);
            if (possibleRes.contains(res)) {
                sum += res;
            }
        }
        return sum;
    }

    private Set<Long> calculateRes(long[][] args) {
        Set<Long> res = new HashSet<>();
        if (args[0].length > 2) {
            long[][] newArgs = new long[args.length * 2][args[0].length - 1];
            for (int i = 0; i < newArgs.length; i++) {
                switch (i % 2) {
                    case 0 -> newArgs[i][0] = OperationPart1.PLUS.calculate(args[i / 2][0], args[i / 2][1]);
                    case 1 -> newArgs[i][0] = OperationPart1.MULTIPLY.calculate(args[i / 2][0], args[i / 2][1]);
                }
                System.arraycopy(args[i/2], 2, newArgs[i], 1, newArgs[i].length - 1);
            }
            res = calculateRes(newArgs);
        } else {
            for (long[] arg : args) {
                for (OperationPart1 op : OperationPart1.values()) {
                    res.add(op.calculate(arg[0], arg[1]));
                }
            }
        }
        return res;
    }

    public long partOneRecursionWithAccumulator(List<String> input) {
        long sum = 0;
        for (String s : input) {
            long target = Long.parseLong(StringUtils.substringBefore(s, ":"));
            Long[] args = Arrays.stream(StringUtils.substringAfter(s, ":").trim().split(" ")).map(Long::parseLong).toArray(Long[]::new);
            Set<Long> possibleRes = calculateResWithAccumulator(args, target, 1, new HashSet<>(){{add(args[0]);}});
            if (possibleRes.contains(target)) {
                sum += target;
            }
        }
        return sum;
    }

    private Set<Long> calculateResWithAccumulator(Long[] args, long target, int index, Set<Long> accumulator) {
        if (index == args.length) {
            return accumulator;
        } else {
            Set<Long> newAccumulator = new HashSet<>();
            for (Long l : accumulator) {
                for (OperationPart1 op : OperationPart1.values()) {
                    long opRes = op.calculate(l, args[index]);
                    if (opRes <= target) {
                        newAccumulator.add(opRes);
                    }
                }
            }
            return calculateResWithAccumulator(args, target, index + 1, newAccumulator);
        }
    }


    public long partTwo(List<String> input) {
        long sum = 0;
        for (String s : input) {
            long res = Long.parseLong(StringUtils.substringBefore(s, ":"));
            long[] args = Arrays.stream(StringUtils.substringAfter(s, ": ").split(" ")).mapToLong(Long::parseLong).toArray();
            Set<Long> current = new HashSet<>();
            current.add(args[0]);
            for (int i = 1; i < args.length; i++) {
                long arg = args[i];
                Set<Long> next = new HashSet<>();
                for (long l : current) {
                    for (OperationPart2 op : OperationPart2.values()) {
                        long opRes = op.calculate(l, arg);
                        if (opRes <= res) {
                            next.add(opRes);
                        }
                    }
                }
                current = next;
            }
            if (current.contains(res)) {
                sum += res;
            }
        }
        return sum;
    }

}