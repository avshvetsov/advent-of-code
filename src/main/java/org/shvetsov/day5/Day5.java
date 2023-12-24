package org.shvetsov.day5;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.shvetsov.utils.Utils;

import java.util.*;
import java.util.stream.LongStream;

/**
 * <a href="https://adventofcode.com/2023/day/5">Day 5</a>
 */
public class Day5 {

    public static final String INPUT_PATH = "src/main/resources/day5/input 5 Anton.txt";

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine(INPUT_PATH);
        Day5 day = new Day5();
        System.out.println(day.partOneAnton(input));
        System.out.println(day.partTwoAnton(input));
    }

    public long partOneAnton(List<String> almanacString) {
        String inputString = almanacString.removeFirst();
        almanacString.removeFirst();
        Almanac almanac = new Almanac(almanacString);
        long min = Arrays.stream(StringUtils.substringAfter(inputString, ": ").split(" "))
                .mapToLong(Long::parseLong)
                .map(almanac::transform)
                .min()
                .orElse(0);
        return min;
    }

    public long partTwoAnton(List<String> almanacString) {
        String[] inputStrings = StringUtils.substringAfter(almanacString.removeFirst(), ": ").split(" ");
        almanacString.removeFirst();
        Almanac almanac = new Almanac(almanacString);
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < inputStrings.length; i += 2) {
            ranges.add(new Range(Long.parseLong(inputStrings[i]), Long.parseLong(inputStrings[i + 1])));
        }

        long min = ranges.stream()
                .flatMap(range -> almanac.transformRange(range).stream())
                .mapToLong(Range::getStart)
                .min()
                .orElse(0);
        return min;
    }
    @Data
    private class Almanac {
        private Almanac almanacNextPage;
        private String pageName;

        private List<TransformationRange> transformationRanges = new ArrayList<>();

        public Almanac(List<String> almanacString) {
            for (String almanacRow = almanacString.removeFirst(); !almanacString.isEmpty() && !almanacRow.isEmpty(); almanacRow = almanacString.removeFirst()) {
                if (almanacRow.contains(":")) {
                    this.pageName = StringUtils.substringBefore(almanacRow, ":");
                } else {
                    this.transformationRanges.add(new TransformationRange(almanacRow));
                }
            }
            if (!almanacString.isEmpty()) {
                this.almanacNextPage = new Almanac(almanacString);
            }
        }

        public long transform(long input) {
            long output = transformationRanges.stream()
                    .filter(trans -> input >= trans.getSourceRangeStart() && input < trans.getSourceRangeEndExclusively())
                    .findFirst()
                    .orElseGet(TransformationRange::new)
                    .transform(input);
            if (almanacNextPage != null) {
                return almanacNextPage.transform(output);
            }
            return output;
        }

        public List<Range> transformRange(Range input) {
            List<TransformationRange> knifes = new ArrayList<>();
            for (TransformationRange transformationRange :
                    transformationRanges) {
                if (input.isCrossedWith(transformationRange)) {
                    knifes.add(transformationRange);
                }
            }

            List<Range> output = input.sliceAndTransform(knifes);

            if (almanacNextPage != null) {
                List<Range> subSlicedRanges = new ArrayList<>();
                for (Range range :
                        output) {
                    subSlicedRanges.addAll(almanacNextPage.transformRange(range));
                }
                return subSlicedRanges;
            }
            return output;
        }

    }
    @Data
    private class TransformationRange {
        private final long destRangeStart;
        private final long sourceRangeStart;

        private final long rangeLength;

        private TransformationRange() {
            this.destRangeStart = 0;
            this.sourceRangeStart = 0;
            this.rangeLength = 0;
        }

        private TransformationRange(String transformationString) {
            String[] input = transformationString.split(" ");
            this.destRangeStart = Long.parseLong(input[0]);
            this.sourceRangeStart = Long.parseLong(input[1]);
            this.rangeLength = Long.parseLong(input[2]);
        }

        private long getSourceRangeEndExclusively() {
            return sourceRangeStart + rangeLength;
        }
        private long transform(long input) {
            return destRangeStart + input - sourceRangeStart;
        }

    }


    @Data
    @AllArgsConstructor
    private class Range {
        private long start;
        private long length;

        public boolean isCrossedWith(TransformationRange transformationRange) {
            return start < transformationRange.getSourceRangeEndExclusively() && getEndExclusively() > transformationRange.sourceRangeStart;
        }

        private long getEndExclusively() {
            return start + length;
        }

        private List<Range> sliceAndTransform(List<TransformationRange> knife) {
            List<Range> slicedRange = new ArrayList<>();
            long[] array = LongStream.concat(LongStream.of(this.getStart(), this.getEndExclusively())
                            , knife.stream()
                                    .flatMapToLong(transformationRange -> LongStream.of(transformationRange.sourceRangeStart, transformationRange.getSourceRangeEndExclusively()))
                                    .filter(value -> value >= this.getStart() && value <= this.getEndExclusively()))
                    .sorted()
                    .distinct()
                    .toArray();
            for (int i = 0; i < array.length - 1; i++) {
                slicedRange.add(new Range(array[i], array[i + 1] - array[i]));
            }

            for (Range range : slicedRange) {
                for (TransformationRange transformationRange : knife) {
                    if (range.isCrossedWith(transformationRange)) {
                        range.setStart(transformationRange.transform(range.getStart()));
                    }
                }
            }
            return slicedRange;
        }
    }

}
