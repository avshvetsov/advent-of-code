package org.shvetsov.day7.dany;


import one.util.streamex.EntryStream;
import org.apache.commons.lang3.tuple.Pair;
import org.shvetsov.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Day7Dany {
    public static void main(String[] args) {

        String[] input = Utils.parseInputDany("day7/input 7 Dany.txt");

        long result1 = part1(input);
        long result2 = part2(input);

        // 251889833 249864828 251889833 251889833
        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    public static long part1(String[] input) {
        Map<Hand, Long> handToBidMap = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            String[] handToBid = s.split(" ");
            Hand hand = new Hand(handToBid[0].toCharArray());
            long bid = Long.parseLong(handToBid[1]);
            handToBidMap.put(hand, bid);
        }

//        EntryStream.of(handToBidMap.keySet().stream()
//                        .sorted((hp1, hp2) -> Hand.CHARS_COMPARATOR_PART1.compare(hp1, hp2)).toList())
//                .mapKeyValue((number, hand) -> Pair.of((number + 1), Arrays.toString(hand.getCards())) + " " + handToBidMap.get(hand))
//                .toList().forEach(System.out::println);

        return EntryStream.of(handToBidMap.keySet().stream()
                        .sorted(Hand.CHARS_COMPARATOR_PART1).toList())
                .mapKeyValue((number, hand) -> (number + 1) * handToBidMap.get(hand))
                .reduce(0L, Long::sum);
    }

//    EntryStream.of(handToBidMap.keySet().stream()
//                        .sorted((hp1, hp2) -> Hand.COMPARATOR.compare(hp1.getLeft(), hp2.getLeft())).toList())
//            .mapKeyValue((number, hand) -> Pair.of((number + 1), handToBidMap.get(hand)))
//            .toList()

    public static long part2(String[] input) {
        Map<Hand, Long> handToBidMap = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            String[] handToBid = s.split(" ");
            Hand hand = new Hand(handToBid[0].toCharArray());
            long bid = Long.parseLong(handToBid[1]);
            handToBidMap.put(hand, bid);
        }

        EntryStream.of(handToBidMap.keySet().stream()
                        .sorted(Hand.CHARS_COMPARATOR_PART2).toList())
                .mapKeyValue((number, hand) -> Pair.of((number + 1), Arrays.toString(hand.getCards())) + " " + handToBidMap.get(hand) + " " + hand.getHandTypePart2())
                .toList()/*.forEach(System.out::println)*/;

        return EntryStream.of(handToBidMap.keySet().stream()
                        .sorted(Hand.CHARS_COMPARATOR_PART2).toList())
                .mapKeyValue((number, hand) -> (number + 1) * handToBidMap.get(hand))
                .reduce(0L, Long::sum);
    }
}
