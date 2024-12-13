package org.shvetsov.day13;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/13">Day 13</a>
 */
public class Day13 {

    private static final int PRICE_A = 3;
    private static final int PRICE_B = 1;
    private static final long ADDITIONAL = 10000000000000L;

    public long partOne(List<String> input) {
        long result = 0;
        ClawMachine machine = new ClawMachine();
        for (String row : input) {
            switch (row) {
                case String s when s.startsWith("Button A") -> {
                    machine.ax = Integer.parseInt(StringUtils.substringBetween(s, "X+", ","));
                    machine.ay = Integer.parseInt(StringUtils.substringAfter(s, "Y+"));
                }
                case String s when s.startsWith("Button B") -> {
                    machine.bx = Integer.parseInt(StringUtils.substringBetween(s, "X+", ","));
                    machine.by = Integer.parseInt(StringUtils.substringAfter(s, "Y+"));
                }
                case String s when s.startsWith("Prize") -> {
                    machine.tx = Integer.parseInt(StringUtils.substringBetween(s, "X=", ","));
                    machine.ty = Integer.parseInt(StringUtils.substringAfter(s, "Y="));
                }
                case String s when s.isEmpty() -> {
//                    long price = calculateMinimalPrice(machine);
                    result += machine.calculateMinimalPrice();
                    machine = new ClawMachine();
                }
                default -> throw new IllegalArgumentException("Invalid row: " + row);
            }
        }
        result += machine.calculateMinimalPrice();
        return result;
    }

    //old solution
    private long calculateMinimalPrice(ClawMachine machine) {
        long countA = 0;
        long countB = Math.min(100, machine.tx / machine.bx + 1);
        long currentX = machine.bx * countB;
        while (countB >= 0 && countA <= 100) {
            if (currentX < machine.tx) {
                countA++;
                currentX += machine.ax;
            } else if (currentX > machine.tx) {
                countB--;
                currentX -= machine.bx;
            } else {
                if (countA * machine.ay + countB * machine.by == machine.ty) {
                    return countA * PRICE_A + countB * PRICE_B;
                } else {
                    countB--;
                    currentX -= machine.bx;
                }
            }
        }
        return 0;
    }

    public static class ClawMachine {
        long ax;
        long ay;
        long bx;
        long by;
        long tx;
        long ty;

        //calculated on the paper
        private long calculateMinimalPrice() {
            long countBNumerator = ty * ax - tx * ay;
            long countBDenominator = by * ax - bx * ay;
            if (countBNumerator % countBDenominator == 0) {
                long countB = countBNumerator / countBDenominator;
                long countANumerator = tx - bx * countB;
                long countADenominator = ax;
                if (countANumerator % countADenominator == 0) {
                    long countA = countANumerator / countADenominator;
                    return countA * PRICE_A + countB * PRICE_B;
                }
            }
            return 0;
        }
    }

    public long partTwo(List<String> input) {
        long result = 0;
        ClawMachine machine = new ClawMachine();
        for (String row : input) {
            switch (row) {
                case String s when s.startsWith("Button A") -> {
                    machine.ax = Integer.parseInt(StringUtils.substringBetween(s, "X+", ","));
                    machine.ay = Integer.parseInt(StringUtils.substringAfter(s, "Y+"));
                }
                case String s when s.startsWith("Button B") -> {
                    machine.bx = Integer.parseInt(StringUtils.substringBetween(s, "X+", ","));
                    machine.by = Integer.parseInt(StringUtils.substringAfter(s, "Y+"));
                }
                case String s when s.startsWith("Prize") -> {
                    machine.tx = ADDITIONAL + Integer.parseInt(StringUtils.substringBetween(s, "X=", ","));
                    machine.ty = ADDITIONAL + Integer.parseInt(StringUtils.substringAfter(s, "Y="));
                }
                case String s when s.isEmpty() -> {
                    result += machine.calculateMinimalPrice();
                    machine = new ClawMachine();
                }
                default -> throw new IllegalArgumentException("Invalid row: " + row);
            }
        }
        result += machine.calculateMinimalPrice();
        return result;
    }
}