package org.shvetsov.day12;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.day12.Day12.Spring;
import org.shvetsov.utils.Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day12Test {

    private final Day12 day = new Day12();


    @Test
    public void testEqualsOfPair() {
        Pair<String, List<Integer>> pair1 = Pair.of("qwerty", List.of(1, 2, 3));
        Pair<String, List<Integer>> pair2 = Pair.of("qwerty", List.of(1, 2, 3));

        System.out.println(pair1.equals(pair2));

        assertThat(pair1).isEqualTo(pair2);
    }

    @Test
    public void testEqualsOfSpring() {
        Spring spring1 = new Spring("???.### 1,1,3");
        Spring spring2 = new Spring("???.### 1,1,3");

        System.out.println(spring1.equals(spring2));

        assertThat(spring1).isEqualTo(spring2);
    }

    @Test
    public void emptyString() {
        String s = "qwe";
        System.out.println(s.substring(2));
        System.out.println(s.substring(1));
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partOneInputDany(InputAnswer param) {
//        int result = Day12Task.part1(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer());
//    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partTwoInputDany(InputAnswer param) {
//        int result = Day12Task.part2(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer());
//    }


}