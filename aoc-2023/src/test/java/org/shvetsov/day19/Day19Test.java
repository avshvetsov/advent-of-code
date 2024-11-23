package org.shvetsov.day19;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import java.util.BitSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day19Test {

    private final Day19 day = new Day19();

    @Test
    public void testBitSet() {
        BitSet bs1 = new BitSet(4000);
        bs1.set(0, 1);
        BitSet bs2 = new BitSet(4000);
        bs2.set(5, 15);
        bs1.and(bs2);         //  {5, 6, 7, 8, 9}
//        bs1.andNot(bs2);      //  {0, 1, 2, 3, 4}
//        bs1.or(bs2);          //  {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}
//        bs1.xor(bs2);         //  {0, 1, 2, 3, 4, 10, 11, 12, 13, 14}
//        boolean intersects = bs1.intersects(bs2);
        System.out.println(bs1);
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_19_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_19_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partOneInputDany(InputAnswer param) {
//        long result = Day19Task.part1(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer().longValue());
//    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_19_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_19_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partTwoInputDany(InputAnswer param) {
//        long result = Day19Task.part2(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer().longValue());
//    }


}