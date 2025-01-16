package org.shvetsov.day22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/22">Day 22</a>
 */
public class Day22 {

    public long partOne(List<String> input, int cycles) {
        long result = 0;
        for (String secretString : input) {
            long secret = Long.parseLong(secretString);
            for (int i = 0; i < cycles; i++) {
                secret = calculateNextSecret(secret);
            }
            result += secret;
        }
        return result;
    }

    private long calculateNextSecret(long secret) {
        secret = ((secret * 64) ^ secret) % 16777216;
        secret = ((secret / 32) ^ secret) % 16777216;
        secret = ((secret * 2048) ^ secret) % 16777216;
        return secret;
    }


    public long partTwo(List<String> input, int cycles) {
        Map<BuyerSequence, Integer> buyerSequencePriceMap = new HashMap<>();
        for (int buyerId = 0; buyerId < input.size(); buyerId++) {
            String secretString = input.get(buyerId);
            long secret = Long.parseLong(secretString);
            int prevPrice = (int) (secret % 10);
            List<Integer> sequence = new ArrayList<>();
            for (int i = 0; i < cycles; i++) {
                secret = calculateNextSecret(secret);
                int price = (int) (secret % 10);
                sequence.add(price - prevPrice);
                if (sequence.size() == 4) {
                    BuyerSequence key = new BuyerSequence(buyerId, sequence.toString());
                    buyerSequencePriceMap.putIfAbsent(key, price);
                } else if (sequence.size() > 4) {
                    sequence.removeFirst();
                    BuyerSequence key = new BuyerSequence(buyerId, sequence.toString());
                    buyerSequencePriceMap.putIfAbsent(key, price);
                }
                prevPrice = price;
            }
        }
        return buyerSequencePriceMap.entrySet().stream()
                .collect(Collectors.groupingBy(e -> e.getKey().sequence(), Collectors.summingInt(Map.Entry::getValue)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("Something went wrong"))
                .getValue();
    }

    public record BuyerSequence(int buyerId, String sequence) {
    }

}