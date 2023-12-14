package org.shvetsov;

import org.junit.jupiter.api.Test;
import org.shvetsov.day6.Day6;
import org.shvetsov.day6.Day6Dany;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {

    private static final String EXAMPLE_PATH = "src/main/resources/example 6-1, 6-2.txt";
    private static final String EXAMPLE_NAME = "example 6-1, 6-2.txt";
    private static final int EXAMPLE_ANSWER_PART_1 = 288;
    private static final int EXAMPLE_ANSWER_PART_2 = 71503;

    private static final int INPUT_ANSWER_PART_1 = 503424;
    private static final int INPUT_ANSWER_PART_2 = 32607562;


    private final Day6 day = new Day6();


    @Test
    public void partOneExample() {
        assertThat(day.partOneAnton(Utils.parseInputByNewLine(EXAMPLE_PATH))).isEqualTo(EXAMPLE_ANSWER_PART_1);
    }

    @Test
    public void partOneInput() {
        int result = day.partOneAnton(Utils.parseInputByNewLine(Day6.INPUT_PATH));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_1);
    }

    @Test
    public void partOneInputDany() {
        int result = Day6Dany.part1(Utils.parseInputDany(Day6Dany.INPUT_NAME));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_1);
    }

    @Test
    public void partTwoExample() {
        assertThat(day.partTwoAnton(Utils.parseInputByNewLine(EXAMPLE_PATH))).isEqualTo(EXAMPLE_ANSWER_PART_2);
    }

    @Test
    public void partTwoInput() {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(Day6.INPUT_PATH));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_2);
    }

    @Test
    public void partTwoInputDany() {
        long result = Day6Dany.part2(Utils.parseInputDany(Day6Dany.INPUT_NAME));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_2);
    }

}