package org.shvetsov.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day4Dany {

    public static final String INPUT_NAME = "input 4 Anton.txt";

    public static int part1(String[] input) {
        int result = 0;
        for (String str : input) {
            String[] cardNumbers = str.substring(str.indexOf(": ") + 2).split(" \\| ");
            Set<Integer> winningNumbers = Arrays.stream(cardNumbers[0].split(" ")).filter(Predicate.not(String::isEmpty)).map(Integer::parseInt).collect(Collectors.toSet());
            Set<Integer> availableNumbers = Arrays.stream(cardNumbers[1].split(" ")).filter(Predicate.not(String::isEmpty)).map(Integer::parseInt).collect(Collectors.toSet());
            winningNumbers.retainAll(availableNumbers);
            result += Math.pow(2.0, winningNumbers.size() - 1);
        }
        return result;
    }

    public static int part2(String[] input) {
        Map<Integer, Integer> cardNumberToCopiesNumber = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            cardNumberToCopiesNumber.put(i + 1, 1);
        }
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            String[] cardNumbers = str.substring(str.indexOf(": ") + 2).split(" \\| ");
            int cardNumber = i + 1;
            Set<Integer> winningNumbers = Arrays.stream(cardNumbers[0].split(" ")).filter(Predicate.not(String::isEmpty)).map(Integer::parseInt).collect(Collectors.toSet());
            Set<Integer> availableNumbers = Arrays.stream(cardNumbers[1].split(" ")).filter(Predicate.not(String::isEmpty)).map(Integer::parseInt).collect(Collectors.toSet());
            winningNumbers.retainAll(availableNumbers);
            int winningScore = winningNumbers.size();
            for (int j = 0; j < winningScore; j++) {
                int cardCopyIndex = cardNumber + j + 1;
                int iterations = cardNumberToCopiesNumber.get(cardNumber);
                cardNumberToCopiesNumber.merge(cardCopyIndex, iterations, Integer::sum);
            }
        }

        return cardNumberToCopiesNumber.values().stream().reduce(0, Integer::sum);
    }

}
