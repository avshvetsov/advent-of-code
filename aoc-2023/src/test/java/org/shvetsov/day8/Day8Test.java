package org.shvetsov.day8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.day8.dany.Day8Dany;
import org.shvetsov.utils.Utils;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day8Test {

    private final Day8 day = new Day8();

    @Test
    public void lcmTest() {
        assertThat(new Day8().lcm(BigInteger.valueOf(12), BigInteger.valueOf(8))).isEqualTo(BigInteger.valueOf(24));
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_8_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        int result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_8_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInputDany(InputAnswer param) {
        int result = Day8Dany.part1(Utils.parseInputDany(param.getInput(NAME)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_8_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        BigInteger result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_8_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInputDany(InputAnswer param) {
        BigInteger result = Day8Dany.part2(Utils.parseInputDany(param.getInput(NAME)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

}