package org.shvetsov;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.shvetsov.day1.Day1;

import java.util.Map;
import java.util.OptionalLong;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    private static final Integer DAY_1_PART_1_CORRECT = 54390;
    private static final Integer DAY_1_PART_2_CORRECT = 54277;

    @Test
    public void day1Part1() {
        assertThat(new Day1().partOneAnton(Utils.parseInputByNewLine("src/main/resources/input 1 Anton.txt")))
                .isEqualTo(DAY_1_PART_1_CORRECT);
    }
    @Test
    public void day1Part1Alex() {
        assertThat(new Day1().partOneAlex().orElse(-1L))
                .isEqualTo(OptionalLong.empty().orElse(DAY_1_PART_1_CORRECT));
    }

    @Test
    public void day1Part1Dany() {
        assertThat(Day1.partOneDany(Utils.parseInputByNewLine("src/main/resources/input 1 Anton.txt").toArray(String[]::new)))
                .isEqualTo(DAY_1_PART_1_CORRECT);
    }



    @Test
    public void day1Part2() {
        assertThat(new Day1().partTwoAnton(Utils.parseInputByNewLine("src/main/resources/input 1 Anton.txt")))
                .isEqualTo(DAY_1_PART_2_CORRECT);
    }

    @Test
    public void day1Part2Dany() {
        assertThat(Day1.partTwoDany(Utils.parseInputByNewLine("src/main/resources/input 1 Anton.txt").toArray(String[]::new)))
                .isEqualTo(DAY_1_PART_2_CORRECT);
    }




    @Test
    @Disabled
    public void test() {
        Map<String, Integer> digitValuesMap = Map.of("one", 1, "two", 2, "three", 3
                , "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);
        Map<String, Integer> reverseDigitValuesMap = digitValuesMap.entrySet().stream()
                .collect(toMap(e -> new StringBuilder(e.getKey()).reverse().toString(), Map.Entry::getValue));
        System.out.println(digitValuesMap);
        System.out.println(reverseDigitValuesMap);
    }

}