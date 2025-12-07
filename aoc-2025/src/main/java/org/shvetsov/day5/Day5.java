package org.shvetsov.day5;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/5">Day 5</a>
 */
public class Day5 {

    //TODO: have to try implement my own RangeSet
    public long partOne(List<String> input) {
        RangeSet<Long> rangeSet = TreeRangeSet.create();
        int i = 0;
        for (; !input.get(i).isBlank(); i++) {
            String[] range = input.get(i).split("-");
            rangeSet.add(Range.closed(Long.parseLong(range[0]), Long.parseLong(range[1])));
        }
        i++;
        int fresh = 0;
        for (; i < input.size(); i++) {
            if (rangeSet.contains(Long.parseLong(input.get(i)))) {
                fresh++;
            }
        }
        return fresh;
    }

    public long partTwo(List<String> input) {
        RangeSet<Long> rangeSet = TreeRangeSet.create();
        for (int i = 0; !input.get(i).isBlank(); i++) {
            String[] range = input.get(i).split("-");
            rangeSet.add(Range.closed(Long.parseLong(range[0]), Long.parseLong(range[1])));
        }
        long fresh = 0;
        for (Range<Long> range : rangeSet.asRanges()) {
            fresh += range.upperEndpoint() - range.lowerEndpoint() + 1;
        }
        return fresh;
    }

}
