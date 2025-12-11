package org.shvetsov.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day10Test {

    private Day10 day;

    @BeforeEach
    public void setup() {
        day = new Day10();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @Test
    void name() {
//        (1,2,3,4,6,7,8) (4,9) (2,3,6,7,9) (0,3,7,8) (0,3,5,8) (0,4,5,6) (4,5,6,8) (1,2,4,6,7,9)
        List<List<Integer>> buttons = List.of(List.of(4, 9), List.of(2, 3, 6, 7, 9), List.of(0, 3, 7, 8), List.of(0, 3, 5, 8), List.of(0, 4, 5, 6), List.of(4, 5, 6, 8), List.of(1, 2, 3, 4, 6, 7, 8), List.of(1, 2, 4, 6, 7, 9));
        int[] joltage = new int[]{29,3,15,31,45,32,44,31,38,28};
        System.out.println(day.calculateMinButtonsJoltage(buttons, joltage));
    }
}
