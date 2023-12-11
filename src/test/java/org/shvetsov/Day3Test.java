package org.shvetsov;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.shvetsov.day3.Day3;
import org.shvetsov.day3.Day3Dany;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    private static final String EXAMPLE_PATH = "src/main/resources/example 3-1, 3-2.txt";
    private static final String EXAMPLE_NAME = "example 3-1, 3-2.txt";
    private static final Integer EXAMPLE_DAY_3_PART_1_CORRECT = 4366;
    private static final Integer EXAMPLE_DAY_3_PART_2_CORRECT = 467835;

    private static final Integer DAY_3_PART_1_CORRECT = 507214;
    private static final Integer DAY_3_PART_2_CORRECT = 72553319;
    private final Day3 day3 = new Day3();

    @Test
    public void parseInputAsTwoDimArray() {
        char[][] chars = Utils.parseInputInTwoDimArray(EXAMPLE_PATH);
        assertThat(chars[0].length).isEqualTo(10);
        assertThat(chars.length).isEqualTo(10);
        assertThat(chars[4][2]).isEqualTo('7');
    }

    @Test
    public void partOneExample() {
        assertThat(day3.partOneAnton(Utils.parseInputInTwoDimArray(EXAMPLE_PATH))).isEqualTo(EXAMPLE_DAY_3_PART_1_CORRECT);
    }

    @Test
    public void partOneInput() {
        assertThat(day3.partOneAnton(Utils.parseInputInTwoDimArray(Day3.INPUT_PATH))).isEqualTo(DAY_3_PART_1_CORRECT);
    }

    @Test
    public void partTwoExample() {
        assertThat(day3.partTwoAnton(Utils.parseInputInTwoDimArray(EXAMPLE_PATH))).isEqualTo(EXAMPLE_DAY_3_PART_2_CORRECT);
    }

    @Test
    public void partTwoInput() {
        assertThat(day3.partTwoAnton(Utils.parseInputInTwoDimArray(Day3.INPUT_PATH))).isEqualTo(DAY_3_PART_2_CORRECT);
    }

    @Test
    public void partOneExampleDany() {
        assertThat(Day3Dany.part1(Utils.parseInputDany(EXAMPLE_NAME))).isEqualTo(EXAMPLE_DAY_3_PART_1_CORRECT);
    }

    @Test
    public void partOneInputDany() {
        assertThat(Day3Dany.part1(Utils.parseInputDany(Day3Dany.INPUT_NAME))).isEqualTo(DAY_3_PART_1_CORRECT);
    }

    @Test
    @Disabled
    public void partTwoExampleDany() {
        assertThat(Day3Dany.part2(Utils.parseInputDany(EXAMPLE_NAME))).isEqualTo(EXAMPLE_DAY_3_PART_2_CORRECT);
    }

    @Test
    public void partTwoInputDany() {
        assertThat(Day3Dany.part2(Utils.parseInputDany(Day3Dany.INPUT_NAME))).isEqualTo(DAY_3_PART_2_CORRECT);
    }
}