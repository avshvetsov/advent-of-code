package org.shvetsov;

import org.junit.jupiter.api.Test;
import org.shvetsov.day3.Day3;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    private static final String EXAMPLE_PATH = "src/main/resources/example 3-1, 3-2.txt";
    private static final Integer EXAMPLE_DAY_3_PART_1_CORRECT = 4366;
    private static final Integer EXAMPLE_DAY_3_PART_2_CORRECT = 467835;

    private static final Integer DAY_3_PART_1_CORRECT = 507214;
    private static final Integer DAY_3_PART_2_CORRECT = -1;
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
        assertThat(new Day3().partOneAnton(Utils.parseInputInTwoDimArray(EXAMPLE_PATH))).isEqualTo(EXAMPLE_DAY_3_PART_1_CORRECT);
    }

    @Test
    public void partOneInput() {
        assertThat(new Day3().partOneAnton(Utils.parseInputInTwoDimArray(Day3.INPUT_PATH))).isEqualTo(DAY_3_PART_1_CORRECT);
    }
}