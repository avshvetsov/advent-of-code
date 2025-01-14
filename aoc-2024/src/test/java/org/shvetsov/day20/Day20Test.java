package org.shvetsov.day20;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day20Test {

    private Day20 day;

    @BeforeEach
    public void setup() {
        day = new Day20();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_20_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_20_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneGeneralInput(InputAnswer param) {
        long result = day.partOneGeneral(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0], (int) param.getAdditionalInput()[1]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_20_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0], (int) param.getAdditionalInput()[1]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }
}