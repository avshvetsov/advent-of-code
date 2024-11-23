package org.shvetsov.day8;

import lombok.Getter;
import one.util.streamex.EntryStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://adventofcode.com/2023/day/8">Day 8</a>
 */
public class Day8 {

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> desertMapString) {
        int result = 0;
        DesertMap desertMap = new DesertMap(desertMapString);
        String currentNode = "AAA";
        while (!currentNode.equals("ZZZ")) {
            char direction = desertMap.navigation[result % desertMap.navigation.length];
            if (direction == 'L') {
                currentNode = desertMap.network.get(currentNode).getLeft();
            } else currentNode = desertMap.network.get(currentNode).getRight();
            result++;
        }
        return result;
    }

    public int partTwoAntonBruteforce(List<String> desertMapString) {
        int result = 0;
        DesertMap desertMap = new DesertMap(desertMapString);
        List<Map.Entry<String, Pair<String, String>>> currentNodes = EntryStream.of(desertMap.network)
                .filter(stringPairEntry -> stringPairEntry.getKey().endsWith("A"))
                .toList();
        while (!isTheEnd(currentNodes)) {
            char direction = desertMap.navigation[result % desertMap.navigation.length];
            if (direction == 'L') {
                List<String> nodes = currentNodes.stream()
                        .map(entry -> entry.getValue().getLeft())
                        .toList();
                currentNodes = getEntries(desertMap, nodes);
            } else {
                List<String> nodes = currentNodes.stream()
                        .map(entry -> entry.getValue().getRight())
                        .toList();
                currentNodes = getEntries(desertMap, nodes);
            }
            result++;
        }
        return result;
    }

    private boolean isTheEnd(List<Map.Entry<String, Pair<String, String>>> currentNodes) {
        return currentNodes.stream()
                .allMatch(stringPairEntry -> stringPairEntry.getKey().endsWith("Z"));
    }

    private List<Map.Entry<String, Pair<String, String>>> getEntries(DesertMap desertMap, List<String> nodes) {
        return EntryStream.of(desertMap.network)
                .filter(stringPairEntry -> nodes.contains(stringPairEntry.getKey()))
                .toList();
    }

    public BigInteger partTwoAnton(List<String> desertMapString) {
        DesertMap desertMap = new DesertMap(desertMapString);
        return EntryStream.of(desertMap.network)
                .filter(stringPairEntry -> stringPairEntry.getKey().endsWith("A"))
                .mapToInt(entry -> {
                    int loopSize = 0;
                    String currentNode = entry.getKey();
                    while (!isTheEnd(currentNode)) {
                        char direction = desertMap.navigation[loopSize % desertMap.navigation.length];
                        currentNode = move(desertMap, currentNode, direction);
                        loopSize++;
                    }
                    return loopSize;
                })
                .mapToObj(BigInteger::valueOf)
                .reduce(this::lcm)
                .orElse(BigInteger.ZERO);
    }

    private boolean isTheEnd(String nodeString) {
        return nodeString.endsWith("Z");
    }

    private String move(DesertMap desertMap, String node, char direction) {
        if (direction == 'L') {
            return desertMap.network.get(node).getLeft();
        } else return desertMap.network.get(node).getRight();
    }

    public BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }


    @Getter
    public static class DesertMap {
        private final char[] navigation;
        private final Map<String, Pair<String, String>> network = new HashMap<>();

        public DesertMap(List<String> desertMapString) {
            this.navigation = desertMapString.remove(0).toCharArray();
            desertMapString.remove(0);
            for (String node :
                    desertMapString) {
                this.network.put(StringUtils.substringBefore(node, " =")
                        , Pair.of(StringUtils.substringBetween(node, "(", ","), StringUtils.substringBetween(node, ", ", ")")));
            }
        }
    }
}

