package org.shvetsov.day11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day11Test {

    private Day11 day;

    @BeforeEach
    public void setup() {
        day = new Day11();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_11_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_11_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_11_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoDanyInput(InputAnswer param) {
        long result = day.partTwoDany(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }
}