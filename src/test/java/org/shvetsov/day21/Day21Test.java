package org.shvetsov.day21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day21Test {

    private Day21 day;

    @BeforeEach
    public void setup() {
        day = new Day21();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_21_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput());
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_21_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput());
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }
}