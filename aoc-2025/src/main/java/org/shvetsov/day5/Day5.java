package org.shvetsov.day5;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/5">Day 5</a>
 */
public class Day5 {

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

    public long partOneTreeMap(List<String> input) {
        // { } - existing ranges
        // < > - new ranges
        NavigableMap<Long, Long> ranges = new TreeMap<>();
        int i = 0;
        for (; !input.get(i).isBlank(); i++) {
            String[] range = input.get(i).split("-");
            long left = Long.parseLong(range[0]);
            long right = Long.parseLong(range[1]);
            NavigableMap<Long, Long> headMap = ranges.headMap(right, true);
            Map.Entry<Long, Long> lastEntry = headMap.lastEntry();
            long newLeft;
            long newRight = lastEntry == null ? right : Math.max(right, lastEntry.getValue());
            while (true) {
                // < > { }
                // < { } >
                // < { > }
                if (lastEntry == null) {
                    newLeft = left;
                    break;
                } else if (lastEntry.getKey() > left) {
                    ranges.remove(lastEntry.getKey());
                } else {
                    // { < } >
                    if (lastEntry.getValue() >= left) {
                        newLeft = lastEntry.getKey();
                    }
                    // { } < { } >
                    else {
                        newLeft = left;
                    }
                    break;
                }
                lastEntry = headMap.lastEntry();
            }
            ranges.put(newLeft, newRight);
        }
        i++;
        int fresh = 0;
        for (; i < input.size(); i++) {
            long id = Long.parseLong(input.get(i));
            Map.Entry<Long, Long> floor = ranges.floorEntry(id);
            if (floor != null && floor.getValue() >= id) {
                fresh++;
            }
        }
        return fresh;
    }

    public long partTwoTreeMap(List<String> input) {
        // { } - existing ranges
        // < > - new ranges
        NavigableMap<Long, Long> ranges = new TreeMap<>();
        int i = 0;
        for (; !input.get(i).isBlank(); i++) {
            String[] range = input.get(i).split("-");
            long left = Long.parseLong(range[0]);
            long right = Long.parseLong(range[1]);
            NavigableMap<Long, Long> headMap = ranges.headMap(right, true);
            Map.Entry<Long, Long> lastEntry = headMap.lastEntry();
            long newLeft;
            long newRight = lastEntry == null ? right : Math.max(right, lastEntry.getValue());
            while (true) {
                // < > { }
                // < { } >
                // < { > }
                if (lastEntry == null) {
                    newLeft = left;
                    break;
                } else if (lastEntry.getKey() > left) {
                    ranges.remove(lastEntry.getKey());
                } else {
                    // { < } >
                    if (lastEntry.getValue() >= left) {
                        newLeft = lastEntry.getKey();
                    }
                    // { } < { } >
                    else {
                        newLeft = left;
                    }
                    break;
                }
                lastEntry = headMap.lastEntry();
            }
            ranges.put(newLeft, newRight);
        }
        long fresh = 0;
        for (Map.Entry<Long, Long> range : ranges.entrySet()) {
            fresh += range.getValue() - range.getKey() + 1;
        }
        return fresh;
    }

}
