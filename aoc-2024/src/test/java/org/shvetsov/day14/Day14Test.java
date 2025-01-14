package org.shvetsov.day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.DAY_14_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day14Test {

    private Day14 day;

    @BeforeEach
    public void setup() {
        day = new Day14();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_14_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)), (int[]) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_14_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)), (int[]) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @Test
    @Disabled
    void printer() {
        day.partTwoPrinter(Utils.parseInputByNewLine(DAY_14_PART_2_REAL_ANTON.getInput(PATH)), (int[]) DAY_14_PART_2_REAL_ANTON.getAdditionalInput()[0]);
    }
}