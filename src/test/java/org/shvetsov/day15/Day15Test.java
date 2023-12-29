package org.shvetsov.day15;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day15Test {

    private final Day15 day = new Day15();

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        int result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partOneInputDany(InputAnswer param) {
//        int result = Day15Task.part1(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer());
//    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        int result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partTwoInputDany(InputAnswer param) {
//        int result = Day15Task.part2(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer());
//    }

}