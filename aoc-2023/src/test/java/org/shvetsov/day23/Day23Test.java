package org.shvetsov.day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day23Test {

    private Day23 day;

    @BeforeEach
    public void setup() {
        day = new Day23();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_23_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    //run with -Xss4m
    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_23_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @Disabled
    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_23_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInputParallel(InputAnswer param) {
        long result = day.partTwoAntonParallel(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }
}