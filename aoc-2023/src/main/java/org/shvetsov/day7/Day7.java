package org.shvetsov.day7;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/7">Day 7</a>
 */
public class Day7 {

    public static final Map<Character, Integer> cardsPartOne = new HashMap<>();
    public static final Map<Character, Integer> cardsPartTwo = new HashMap<>();

    static {
        cardsPartOne.put('2', 2);
        cardsPartOne.put('3', 3);
        cardsPartOne.put('4', 4);
        cardsPartOne.put('5', 5);
        cardsPartOne.put('6', 6);
        cardsPartOne.put('7', 7);
        cardsPartOne.put('8', 8);
        cardsPartOne.put('9', 9);
        cardsPartOne.put('T', 10);
        cardsPartOne.put('J', 11);
        cardsPartOne.put('Q', 12);
        cardsPartOne.put('K', 13);
        cardsPartOne.put('A', 14);

        cardsPartTwo.put('J', 1);
        cardsPartTwo.put('2', 2);
        cardsPartTwo.put('3', 3);
        cardsPartTwo.put('4', 4);
        cardsPartTwo.put('5', 5);
        cardsPartTwo.put('6', 6);
        cardsPartTwo.put('7', 7);
        cardsPartTwo.put('8', 8);
        cardsPartTwo.put('9', 9);
        cardsPartTwo.put('T', 10);
        cardsPartTwo.put('Q', 12);
        cardsPartTwo.put('K', 13);
        cardsPartTwo.put('A', 14);
    }

    public static void main(String[] args) {
        System.out.println(Integer.compare(1, 2));

        char ch = '8';
        char ch2 = 'Q';
        System.out.println(cardsPartOne.get(ch));
        System.out.println(cardsPartOne.get(ch2));

        Character[] chs = new Character[]{'A', 'A', 'K', '8', 'A'};
        Map<Character, Long> collect = Arrays.stream(chs).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect);

    }

    public long partOneAnton(List<String> gameString) {
        long result = 0L;
        Hand[] hands = gameString.stream()
                .map(Hand::new)
                .sorted()
                .toArray(Hand[]::new);
        for (int i = 0; i < hands.length; i++) {
            result += (long) hands[i].bid * (i + 1);
        }
        return result;
    }

    public long partTwoAnton(List<String> gameString) {
        long result = 0L;
        HandPartTwo[] hands = gameString.stream()
                .map(HandPartTwo::new)
                .sorted()
                .toArray(HandPartTwo[]::new);
        for (int i = 0; i < hands.length; i++) {
            result += (long) hands[i].bid * (i + 1);
        }
        return result;
    }


    @Getter
    private class Hand implements Comparable<Hand> {

        private final Character[] hand;
        private final Combination combination;
        private final int bid;

        public Hand(String gameString) {
            String handString = StringUtils.substringBefore(gameString, " ");
            this.hand = IntStream.range(0, handString.length())
                    .mapToObj(handString::charAt)
                    .toArray(Character[]::new);
            this.combination = Combination.getByHand(this.hand);
            this.bid = Integer.parseInt(StringUtils.substringAfter(gameString, " "));
        }

        @Override
        public int compareTo(Hand handToCompare) {
            int res;
            res = Integer.compare(this.combination.strength, handToCompare.combination.strength);
            if (res == 0) {
                for (int i = 0; i < this.hand.length; i++) {
                    res = Integer.compare(cardsPartOne.get(hand[i]), cardsPartOne.get(handToCompare.hand[i]));
                    if (res != 0) {
                        break;
                    }
                }
            }
            return res;
        }
    }

    @Getter
    private class HandPartTwo implements Comparable<HandPartTwo> {

        private final Character[] hand;
        private final Combination combination;
        private final int bid;

        public HandPartTwo(String gameString) {
            String handString = StringUtils.substringBefore(gameString, " ");
            this.hand = IntStream.range(0, handString.length())
                    .mapToObj(handString::charAt)
                    .toArray(Character[]::new);
            this.combination = Combination.getByHandPartTwo(this.hand);
            this.bid = Integer.parseInt(StringUtils.substringAfter(gameString, " "));
        }

        @Override
        public int compareTo(HandPartTwo handToCompare) {
            int res = 0;
            res = Integer.compare(this.combination.strength, handToCompare.combination.strength);
            if (res == 0) {
                for (int i = 0; i < this.hand.length; i++) {
                    res = Integer.compare(cardsPartTwo.get(hand[i]), cardsPartTwo.get(handToCompare.hand[i]));
                    if (res != 0) {
                        break;
                    }
                }
            }
            return res;
        }
    }


    public enum Combination {
        FIVE_OF_KINGS(7, null),
        FOUR_OF_KINGS(6, FIVE_OF_KINGS),
        FULL_HOUSE(5, null),
        THREE_OF_KINGS(4, FOUR_OF_KINGS),
        TWO_PAIR(3, FULL_HOUSE),
        ONE_PAIR(2, THREE_OF_KINGS),
        HIGH_CARD(1, ONE_PAIR);

        private final int strength;
        private final Combination upgrade;

        Combination(int strength, Combination upgrade) {
            this.strength = strength;
            this.upgrade = upgrade;
        }

        public static Combination getByHand(Character[] hand) {
            Combination res = null;
            Map<Character, Long> map = Arrays.stream(hand).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            switch (map.size()) {
                case 5 -> res = HIGH_CARD;
                case 4 -> res = ONE_PAIR;
                case 3 -> {
                    if (map.containsValue(3L)) {
                        res = THREE_OF_KINGS;
                    } else res = TWO_PAIR;
                }
                case 2 -> {
                    if (map.containsValue(4L)) {
                        res = FOUR_OF_KINGS;
                    } else res = FULL_HOUSE;
                }
                case 1 -> res = FIVE_OF_KINGS;
            }
            return res;
        }

        public static Combination getByHandPartTwo(Character[] hand) {
            Combination res = null;
            Map<Character, Long> map = Arrays.stream(hand).filter(ch -> ch != 'J').collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int wildcardsCount = hand.length - map.values().stream().mapToInt(Long::intValue).sum();
            switch (map.size() + wildcardsCount) {
                case 5 -> res = HIGH_CARD;
                case 4 -> res = ONE_PAIR;
                case 3 -> {
                    if (map.containsValue(3L)) {
                        res = THREE_OF_KINGS;
                    } else res = TWO_PAIR;
                }
                case 2 -> {
                    if (map.containsValue(4L)) {
                        res = FOUR_OF_KINGS;
                    } else res = FULL_HOUSE;
                }
                case 1 -> res = FIVE_OF_KINGS;
                default -> res = HIGH_CARD;
            }
            for (int i = 0; i < wildcardsCount; i++) {
                if (res.upgrade != null) {
                    res = res.upgrade;
                }
            }
            return res;
        }
    }
}
