package org.shvetsov.day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day18Test {

    private final Day18 day = new Day18();

    @Test
    public void hexadecimalParse() {
        String s = "70c71";
        assertThat(Long.parseLong(s, 16)).isEqualTo(461937L);
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_18_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        int result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_18_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partOneInputDany(InputAnswer param) {
//        long result = Day18Task.part1(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer().longValue());
//    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_18_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_18_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partTwoInputDany(InputAnswer param) {
//        long result = Day18Task.part2(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer().longValue());
//    }


}