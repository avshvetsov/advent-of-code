package org.shvetsov.day11;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2022/day/11">Day 11</a>
 */
public class Day11 {

    public long partOne(List<String> input) {
        int rounds = 20;
        List<Monkey> monkeys = buildMonkeysPart1(input);
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                monkey.process(monkeys, true);
            }
        }
        monkeys.sort(Comparator.comparing(Monkey::getObservationCount, Comparator.reverseOrder()));
        return monkeys.get(0).getObservationCount() * monkeys.get(1).getObservationCount();
    }

    private List<Monkey> buildMonkeysPart1(List<String> input) {
        List<Monkey> monkeys = new ArrayList<>();
        Monkey currentMonkey = new Monkey();
        for (String row : input) {
            switch (row) {
                case String s when s.startsWith("Monkey ") ->
                        currentMonkey.setId(Integer.parseInt(StringUtils.substringBetween(s, "Monkey ", ":")));
                case String s when s.startsWith("  Starting items: ") -> currentMonkey.setItemWorries(
                        Arrays.stream(StringUtils.substringAfter(s, "  Starting items: ").split(", "))
                                .map(Long::parseLong)
                                .collect(Collectors.toList()));
                case String s when s.startsWith("  Operation: ") -> {
                    String[] split = StringUtils.substringAfter(s, "  Operation: new = old ").split(" ");

                    UnaryOperator<Long> operation;
                    if (split[0].equals("+")) {
                        operation = integer -> integer + (split[1].equals("old") ? integer : Integer.parseInt(split[1]));
                    } else if (split[0].equals("*")) {
                        operation = integer -> integer * (split[1].equals("old") ? integer : Integer.parseInt(split[1]));
                    } else throw new UnsupportedOperationException("Unknown operation: " + split[0]);
                    currentMonkey.setOperation(operation);
                }
                case String s when s.startsWith("  Test: divisible by ") -> {
                    Long divider = Long.parseLong(StringUtils.substringAfter(s, "  Test: divisible by "));
                    currentMonkey.setTestValue(divider);
                    currentMonkey.setCondition(integer -> integer % divider == 0);
                }
                case String s when s.startsWith("    If true: throw to monkey ") -> {
                    int monkeyId = Integer.parseInt(StringUtils.substringAfter(s, "    If true: throw to monkey "));
                    currentMonkey.setTrueMonkey(mList -> mList.get(monkeyId));
                }
                case String s when s.startsWith("    If false: throw to monkey ") -> {
                    int monkeyId = Integer.parseInt(StringUtils.substringAfter(s, "    If false: throw to monkey "));
                    currentMonkey.setFalseMonkey(mList -> mList.get(monkeyId));
                }
                case String s when s.isEmpty() -> {
                    monkeys.add(currentMonkey);
                    currentMonkey = new Monkey();
                }
                default -> throw new IllegalArgumentException("Unprocessed input: " + row);
            }
        }
        monkeys.add(currentMonkey);
        return monkeys;
    }


    public long partTwo(List<String> input) {
        int rounds = 10000;
        List<Monkey> monkeys = buildMonkeysPart1(input);

//        long lcm = lcm(monkeys.stream().mapToLong(Monkey::getTestValue).toArray());
        long lcm = 1L;
        for (Monkey monkey : monkeys) {
            lcm = ArithmeticUtils.lcm(lcm, monkey.getTestValue());
        }
        for (int i = 0; i < rounds; i++) {
//            System.out.println("Round: " + i);
//            monkeys.forEach(monkey -> System.out.println(monkey.getId() + ": " + monkey.getItemWorries()));
            for (Monkey monkey : monkeys) {
                monkey.process(monkeys, false);
            }
            for (Monkey monkey : monkeys) {
                monkey.divideWorries(lcm);
            }
        }
        monkeys.sort(Comparator.comparing(Monkey::getObservationCount, Comparator.reverseOrder()));
        return monkeys.get(0).getObservationCount() * monkeys.get(1).getObservationCount();
    }


    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static long lcm(long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    public long partTwoDebug(List<String> input) {
        int rounds = 20;
        List<MonkeyBigDecimalDebug> monkeys = buildMonkeysPart2(input);
        for (int i = 0; i < rounds; i++) {
            System.out.println("Round: " + i);
            monkeys.forEach(monkey -> System.out.println(monkey.getId() + ": " + monkey.getItemWorries()));
            for (MonkeyBigDecimalDebug monkey : monkeys) {
                monkey.process(monkeys);
            }
        }
        monkeys.sort(Comparator.comparing(MonkeyBigDecimalDebug::getObservationCount, Comparator.reverseOrder()));
        return (long) monkeys.get(0).getObservationCount() * monkeys.get(1).getObservationCount();
    }


    private List<MonkeyBigDecimalDebug> buildMonkeysPart2(List<String> input) {
        List<MonkeyBigDecimalDebug> monkeys = new ArrayList<>();
        MonkeyBigDecimalDebug currentMonkey = new MonkeyBigDecimalDebug();
        for (String row : input) {
            switch (row) {
                case String s when s.startsWith("Monkey ") ->
                        currentMonkey.setId(Integer.parseInt(StringUtils.substringBetween(s, "Monkey ", ":")));
                case String s when s.startsWith("  Starting items: ") -> currentMonkey.setItemWorries(
                        Arrays.stream(StringUtils.substringAfter(s, "  Starting items: ").split(", "))
                                .map(s1 -> BigInteger.valueOf(Long.parseLong(s1)))
                                .collect(Collectors.toList()));
                case String s when s.startsWith("  Operation: ") -> {
                    String[] split = StringUtils.substringAfter(s, "  Operation: new = old ").split(" ");
                    UnaryOperator<BigInteger> operation;
                    if (split[0].equals("+")) {
                        operation = bi -> bi.add((split[1].equals("old") ? bi : BigInteger.valueOf(Long.parseLong(split[1]))));
                    } else if (split[0].equals("*")) {
                        operation = bi -> bi.multiply((split[1].equals("old") ? bi : BigInteger.valueOf(Long.parseLong(split[1]))));
                    } else throw new UnsupportedOperationException("Unknown operation: " + split[0]);
                    currentMonkey.setOperation(operation);
                }
                case String s when s.startsWith("  Test: divisible by ") -> {
                    BigInteger divider = BigInteger.valueOf(Long.parseLong(StringUtils.substringAfter(s, "  Test: divisible by ")));
                    currentMonkey.setCondition(bi -> bi.remainder(divider).equals(BigInteger.ZERO));
                }
                case String s when s.startsWith("    If true: throw to monkey ") -> {
                    int monkeyId = Integer.parseInt(StringUtils.substringAfter(s, "    If true: throw to monkey "));
                    currentMonkey.setTrueAction((worry, mList) -> mList.get(monkeyId).getItemWorries().add(worry));
                }
                case String s when s.startsWith("    If false: throw to monkey ") -> {
                    int monkeyId = Integer.parseInt(StringUtils.substringAfter(s, "    If false: throw to monkey "));
                    currentMonkey.setFalseAction((worry, mList) -> mList.get(monkeyId).getItemWorries().add(worry));
                }
                case String s when s.isEmpty() -> {
                    monkeys.add(currentMonkey);
                    currentMonkey = new MonkeyBigDecimalDebug();
                }
                default -> throw new IllegalArgumentException("Unprocessed input: " + row);
            }
        }
        monkeys.add(currentMonkey);
        return monkeys;
    }

}