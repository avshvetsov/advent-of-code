package org.shvetsov.day16;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day16Test {

    private final Day16 day = new Day16();

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_16_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_16_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partOneInputDany(InputAnswer param) {
//        long result = Day16Task.part1(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer().longValue());
//    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_16_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_16_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partTwoInputDany(InputAnswer param) {
//        long result = Day16Task.part2(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer().longValue());
//    }


}