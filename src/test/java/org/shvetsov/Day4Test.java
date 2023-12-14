package org.shvetsov;

import org.junit.jupiter.api.Test;
import org.shvetsov.day4.Day4;
import org.shvetsov.day4.Day4Dany;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    private static final String EXAMPLE_PATH = "src/main/resources/example 4.txt";
    private static final String EXAMPLE_NAME = "example 4.txt";
    private static final Integer EXAMPLE_DAY_4_PART_1_CORRECT = 13;
    private static final Integer EXAMPLE_DAY_4_PART_2_CORRECT = 30;

    private static final Integer DAY_4_PART_1_CORRECT = 22897;
    private static final Integer DAY_4_PART_2_CORRECT = 5095824;
    private final Day4 day4 = new Day4();


    @Test
    public void partOneExample() {
        assertThat(day4.partOneAnton(Utils.parseInputByNewLine(EXAMPLE_PATH))).isEqualTo(EXAMPLE_DAY_4_PART_1_CORRECT);
    }

    @Test
    public void partOneInput() {
        int result = day4.partOneAnton(Utils.parseInputByNewLine(Day4.INPUT_PATH));
        System.out.println(result);
        assertThat(result).isEqualTo(DAY_4_PART_1_CORRECT);
    }
    @Test
    public void partOneInputDany() {
        int result = Day4Dany.part1(Utils.parseInputDany(Day4.INPUT_NAME));
        System.out.println(result);
        assertThat(result).isEqualTo(DAY_4_PART_1_CORRECT);
    }

    @Test
    public void partTwoExample() {
        assertThat(day4.partTwoAnton(Utils.parseInputByNewLine(EXAMPLE_PATH))).isEqualTo(EXAMPLE_DAY_4_PART_2_CORRECT);
    }

    @Test
    public void partTwoInput() {
        int result = day4.partTwoAnton(Utils.parseInputByNewLine(Day4.INPUT_PATH));
        System.out.println(result);
        assertThat(result).isEqualTo(DAY_4_PART_2_CORRECT);
    }

    @Test
    public void partTwoInputDany() {
        int result = Day4Dany.part2(Utils.parseInputDany(Day4.INPUT_NAME));
        System.out.println(result);
        assertThat(result).isEqualTo(DAY_4_PART_2_CORRECT);
    }
}