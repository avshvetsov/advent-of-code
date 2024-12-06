package org.shvetsov.day16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day16Test {

    private Day16 day;

    @BeforeEach
    public void setup() {
        day = new Day16();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_16_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_16_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }


    @Disabled
    @Test
    void subSets() {
        List<String> valves = new ArrayList<>();
        valves.add("A");
        valves.add("B");
        valves.add("C");
        for (int i = 0; i < (1 << (valves.size() - 1)); i++) {
            Set<String> set = new HashSet<>();
            Set<String> opposite = new HashSet<>();
            for (int j = 0; j < valves.size(); j++) {
                if ((i & (1 << j)) == 0) {
                    set.add(valves.get(j));
                } else {
                    opposite.add(valves.get(j));
                }
            }
            System.out.println(set + " - " + opposite);
        }
    }
}