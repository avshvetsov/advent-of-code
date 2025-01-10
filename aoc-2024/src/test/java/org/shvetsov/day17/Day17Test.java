package org.shvetsov.day17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day17Test {

    private Day17 day;

    @BeforeEach
    public void setup() {
        day = new Day17();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_17_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        String result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_17_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @Test
    void name() {
        ThreeBitComputer computer = new ThreeBitComputer(15, 0, 0, "2,4,1,2,7,5,4,5,1,3,5,5,0,3,3,0");
        computer.run();
        System.out.println(computer.output);
    }
}