package org.shvetsov.day24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.DAY_24_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day24Test {

    private Day24 day;

    @BeforeEach
    public void setup() {
        day = new Day24();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_24_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_24_PART_2_REAL.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        String result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_24_PART_2_VERIFY.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void verifyPartTwo(InputAnswer param) {
        Long x = (Long) param.getAdditionalInput()[0];
        Long y = (Long) param.getAdditionalInput()[1];
        long result = day.sum(Utils.parseInputByNewLine(param.getInput(PATH)), x, y);
        assertThat(result).isEqualTo(x + y);
    }

    @Test
    @Disabled
    void fixerPartTwo() {
        day.fixer(Utils.parseInputByNewLine(DAY_24_PART_2_REAL_ANTON.getInput(PATH)));
    }
}