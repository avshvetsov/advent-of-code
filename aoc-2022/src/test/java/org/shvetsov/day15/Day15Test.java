package org.shvetsov.day15;

import com.google.common.collect.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

class Day15Test {

    private Day15 day;

    @BeforeEach
    public void setup() {
        day = new Day15();
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOne(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput());
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        long result = day.partTwo(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput());
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_15_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoSingleRangeSetInput(InputAnswer param) {
        long result = day.partTwoSingleRangeSet(Utils.parseInputByNewLine(param.getInput(PATH)), (int) param.getAdditionalInput());
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

    @Test
    @Disabled
    void guavaRanges() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 5));
        rangeSet.add(Range.closed(2, 6));
        rangeSet.add(Range.closed(10, 11));
        int size = 0;
        for (Range<Integer> range : rangeSet.asRanges()) {
            size += ContiguousSet.create(range, DiscreteDomain.integers()).size();
        }
        assertThat(size).isEqualTo(8);
    }

    @Test
    @Disabled
    void guavaRanges2() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 5));
        rangeSet.add(Range.closed(2, 6));
        rangeSet.add(Range.closed(10, 11));

        assertThat(rangeSet.complement().intersects(Range.closed(1, 11))).isTrue();
        assertThat(rangeSet.complement().intersects(Range.closed(1, 6))).isFalse();
    }

    @Test
    @Disabled
    void guavaRanges3() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 5));
        rangeSet.add(Range.closed(6, 8));

        assertThat(rangeSet.complement().intersects(Range.closed(1, 8))).isTrue();
    }
}