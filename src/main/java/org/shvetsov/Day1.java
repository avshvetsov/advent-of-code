package org.shvetsov;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

/**
 *<a href="https://adventofcode.com/2023/day/1">Day 1</a>
 */
public class Day1 {

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine("src/main/resources/input 1-1, 1-2.txt");
        Day1 day = new Day1();
        System.out.println(day.partOne(input));
        System.out.println(day.partTwo(input));
    }

    public int partOne(List<String> input) {
        int sum = 0;
        for (String word :
                input) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (Character.isDigit(ch)) {
                    sum = sum + Character.getNumericValue(ch) * 10;
                    break;
                }
            }
            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);
                if (Character.isDigit(ch)) {
                    sum = sum + Character.getNumericValue(ch);
                    break;
                }
            }
        }
        return sum;
    }



    private static final Map<String, Integer> digitValuesMap = Map.of("one", 1, "two", 2, "three", 3
            , "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);

    private static final Map<String, Integer> reverseDigitValuesMap = digitValuesMap.entrySet().stream()
            .collect(toMap(e -> new StringBuilder(e.getKey()).reverse().toString(), Map.Entry::getValue));

    public int partTwo(List<String> input) {
        int sum = 0;
        for (String word :
                input) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (Character.isDigit(ch)) {
                    sum = sum + Character.getNumericValue(ch) * 10;
                    break;
                }

                int j = i;
                Optional<Integer> digit = digitValuesMap.entrySet().stream()
                        .filter(entry -> word.startsWith(entry.getKey(), j))
                        .findAny()
                        .map(Map.Entry::getValue);
                if (digit.isPresent()) {
                    sum = sum + digit.get() * 10;
                    break;
                }
            }

            String reversedWord = new StringBuilder(word).reverse().toString();
            for (int i = 0; i < reversedWord.length(); i++) {
                char ch = reversedWord.charAt(i);
                if (Character.isDigit(ch)) {
                    sum = sum + Character.getNumericValue(ch);
                    break;
                }

                int j = i;
                Optional<Integer> digit = reverseDigitValuesMap.entrySet().stream()
                        .filter(entry -> reversedWord.startsWith(entry.getKey(), j))
                        .findAny()
                        .map(Map.Entry::getValue);
                if (digit.isPresent()) {
                    sum = sum + digit.get();
                    break;
                }
            }
        }
        return sum;
    }


}
