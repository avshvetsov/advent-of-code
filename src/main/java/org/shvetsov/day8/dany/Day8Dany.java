package org.shvetsov.day8.dany;

import org.apache.commons.lang3.tuple.Pair;
import org.shvetsov.utils.Utils;

import java.math.BigInteger;
import java.util.*;

public class Day8Dany {
    public static void main(String[] args) {

        // max is 12 red cubes, 13 green cubes, and 14 blue cubes
        String[] input = Utils.parseInputDany("day8/input 8 Dany.txt");

        int result1 = part1(input);
        BigInteger result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    public static int part1(String[] input) {
        char[] lrs = input[0].toCharArray();

        Map<String, Pair<String, String>> steps = new HashMap<>();

        for (int i = 2; i < input.length; i++) {
            String[] keyToLr = input[i].split(" = ");
            String key = keyToLr[0];
            String left = keyToLr[1].substring(1, 4);
            String right = keyToLr[1].substring(6, 9);
            steps.put(key, Pair.of(left, right));
        }

        String current = "AAA";
        int counter = 0;
        for (int i = 0; i < lrs.length; i++) {
            Pair<String, String> pair = steps.get(current);
            String next = lrs[i] == 'L' ? pair.getLeft() : pair.getRight();
            current = next;
            counter++;
            if (current.equals("ZZZ")) {
                break;
            } else if (i == lrs.length - 1) {
                i = -1;
            }
        }

        return counter;
    }

    public static BigInteger part2(String[] input) {
        char[] lrs = input[0].toCharArray();

        Map<String, Pair<String, String>> steps = new HashMap<>();

        for (int i = 2; i < input.length; i++) {
            String[] keyToLr = input[i].split(" = ");
            String key = keyToLr[0];
            String left = keyToLr[1].substring(1, 4);
            String right = keyToLr[1].substring(6, 9);
            steps.put(key, Pair.of(left, right));
        }

        List<String> starts = steps.keySet().stream().filter(s -> s.endsWith("A")).toList();
        List<String> current = starts;
        List<BigInteger> result = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < lrs.length; i++) {
            List<Pair<String, String>> pairs = current.stream().map(steps::get).toList();
            current = lrs[i] == 'L' ? pairs.stream().map(Pair::getLeft).toList() : pairs.stream().map(Pair::getRight).toList();
            counter++;
            if (current.stream().allMatch(s -> s.endsWith("Z"))) {
                for (String s : current.stream().filter(s -> s.endsWith("Z")).toList()) {
                    result.add(BigInteger.valueOf(counter));
                }
                break;
            } else if (current.stream().anyMatch(s -> s.endsWith("Z"))) {
                for (String s : current.stream().filter(s -> s.endsWith("Z")).toList()) {
                    result.add(BigInteger.valueOf(counter));
                }
                current = current.stream().filter(s -> !s.endsWith("Z")).toList();
            }
            if (i == lrs.length - 1) {
                i = -1;
            }
        }

        return result.stream().reduce(Day8Dany::lcm).orElse(BigInteger.ONE);
    }

    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }
}