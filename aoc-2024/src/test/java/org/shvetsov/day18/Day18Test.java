package org.shvetsov.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day18Test {

    private Day18 day;

    @BeforeEach
    public void setup() {
        day = new Day18();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_18_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)), ((int[]) param.getAdditionalInput())[0], ((int[]) param.getAdditionalInput())[1]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_18_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        String result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)), ((int[]) param.getAdditionalInput())[0], ((int[]) param.getAdditionalInput())[1]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }
}