package org.shvetsov.day22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day22Test {

    private Day22 day;

    @BeforeEach
    public void setup() {
        day = new Day22();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_22_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_22_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput()[0]);
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @Test
    @Disabled
    void name() {
        long secret = 123;
        secret = ((secret * 64) ^ secret) % 16777216;
        secret = ((secret / 32) ^ secret) % 16777216;
        secret = ((secret * 2048) ^ secret) % 16777216;
        System.out.println(secret);
        secret = ((secret * 64) ^ secret) % 16777216;
        secret = ((secret / 32) ^ secret) % 16777216;
        secret = ((secret * 2048) ^ secret) % 16777216;
        System.out.println(secret);
    }

    @Test
    @Disabled
    void name2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.removeFirst();
        System.out.println(list);
    }
}