package org.shvetsov.day1;

import lombok.SneakyThrows;
import one.util.streamex.EntryStream;
import org.shvetsov.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 *<a href="https://adventofcode.com/2023/day/1">Day 1</a>
 */
public class Day1 {

    public static final String INPUT_PATH = "src/main/resources/day1/input 1 Anton.txt";

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine(INPUT_PATH);
        Day1 day = new Day1();
        System.out.println(day.partOneAnton(input));
        System.out.println(day.partTwoAnton(input));
    }

    public int partOneAnton(List<String> input) {
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

    @SneakyThrows
    public OptionalLong partOneAlex() {
        File file = new File(INPUT_PATH);
        String regex = "[^\\d.]";
        OptionalLong result;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            result = reader
                    .lines()
                    .map(str -> str.replaceAll(regex, ""))
                    .map(str -> str.substring(0,1).concat(str.substring(str.length()-1)))
                    .mapToLong(Long::parseLong)
                    .reduce(Long::sum);
        }
        return result;
    }

    public static int partOneDany(String[] input) {
        int result = 0;
        for (String s : input) {
            int[] ints = s.chars().filter(Character::isDigit).map(Character::getNumericValue).toArray();
            int firstDigit = ints.length > 0 ? ints[0] : 0;
            int lastDigit = ints.length > 0 ? ints[ints.length - 1] : 0;
            result += firstDigit * 10 + lastDigit;
        }
        return result;
    }




    private static final Map<String, Integer> digitValuesMap = Map.of("one", 1, "two", 2, "three", 3
            , "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);

    private static final Map<String, Integer> reverseDigitValuesMap = digitValuesMap.entrySet().stream()
            .collect(toMap(e -> new StringBuilder(e.getKey()).reverse().toString(), Map.Entry::getValue));

    public int partTwoAnton(List<String> input) {
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




    private static final Map<String, Integer> validObjects = new HashMap<>();

    static {
        validObjects.put("1", 1);
        validObjects.put("2", 2);
        validObjects.put("3", 3);
        validObjects.put("4", 4);
        validObjects.put("5", 5);
        validObjects.put("6", 6);
        validObjects.put("7", 7);
        validObjects.put("8", 8);
        validObjects.put("9", 9);
        validObjects.put("one", 1);
        validObjects.put("two", 2);
        validObjects.put("three", 3);
        validObjects.put("four", 4);
        validObjects.put("five", 5);
        validObjects.put("six", 6);
        validObjects.put("seven", 7);
        validObjects.put("eight", 8);
        validObjects.put("nine", 9);
    }
    public static int partTwoDany(String[] input) {
        return Arrays.stream(input).map(str -> {
                    List<Integer> list = EntryStream.of(validObjects)
                            .filterKeys(str::contains)
                            .flatMapKeys(key -> Stream.of(str.indexOf(key), str.lastIndexOf(key)))
                            .sortedBy(Map.Entry::getKey)
                            .values()
                            .toList();
                    int firstDigit = list.isEmpty() ? 0 : list.get(0);
                    int lastDigit = list.isEmpty() ? 0 : list.get(list.size() - 1);
                    return firstDigit * 10 + lastDigit;
                })
                .reduce(Integer::sum).orElse(0);
    }

}
