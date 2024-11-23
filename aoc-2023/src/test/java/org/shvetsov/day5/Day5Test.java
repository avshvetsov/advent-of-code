package org.shvetsov.day5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {

    private static final String EXAMPLE_PATH = "src/main/resources/day5/example 5.txt";
    private static final String EXAMPLE_NAME = "day5/example 5.txt";
    private static final long EXAMPLE_ANSWER_PART_1 = 35L;
    private static final long EXAMPLE_ANSWER_PART_2 = 46L;

    private static final long INPUT_ANSWER_PART_1 = 806029445L;
    private static final long INPUT_ANSWER_PART_2 = 59370572L;
    private final Day5 day = new Day5();


    @Test
    public void partOneExample() {
        assertThat(day.partOneAnton(Utils.parseInputByNewLine(EXAMPLE_PATH))).isEqualTo(EXAMPLE_ANSWER_PART_1);
    }

    @Test
    public void partOneInput() {
        long result = day.partOneAnton(Utils.parseInputByNewLine(Day5.INPUT_PATH));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_1);
    }

    @Test
    public void partTwoExample() {
        assertThat(day.partTwoAnton(Utils.parseInputByNewLine(EXAMPLE_PATH))).isEqualTo(EXAMPLE_ANSWER_PART_2);
    }

    @Test
    public void partTwoInput() {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(Day5.INPUT_PATH));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_2);
    }

    @Test
    @Disabled
    public void partTwoInputDany() {
        long result = Day5Dany.part2(Utils.parseInputDany("day5/input 5 Anton.txt"));
        System.out.println(result);
        assertThat(result).isEqualTo(INPUT_ANSWER_PART_2);
    }


}