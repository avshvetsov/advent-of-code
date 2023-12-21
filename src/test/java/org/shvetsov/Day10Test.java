package org.shvetsov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.day10.Day10;
import org.shvetsov.day10.dany.Day10Task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day10Test {

    private final Day10 day = new Day10();


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        int result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInputDany(InputAnswer param) {
        int result = Day10Task.part1(Utils.parseInputDany(param.getInput(NAME)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        int result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInputDany(InputAnswer param) {
        int result = Day10Task.part2(Utils.parseInputDany(param.getInput(NAME)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

}