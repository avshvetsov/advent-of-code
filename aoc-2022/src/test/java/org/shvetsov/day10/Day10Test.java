package org.shvetsov.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day10Test {

    private Day10 day;

    @BeforeEach
    public void setup() {
        day = new Day10();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_10_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        List<String> result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        for (String s : result) {
            System.out.println(s);
        }
        assertThat(result).isEqualTo(param.getAnswer());
    }
}