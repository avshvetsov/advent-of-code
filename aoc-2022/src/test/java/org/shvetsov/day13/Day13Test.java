package org.shvetsov.day13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day13Test {

    private Day13 day;
    private Day13Ugly dayUgly;

    @BeforeEach
    public void setup() {
        day = new Day13();
        dayUgly = new Day13Ugly();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_13_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_13_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_13_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInputUgly(InputAnswer param) {
        long result = dayUgly.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_13_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInputUgly(InputAnswer param) {
        long result = dayUgly.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }
}