package org.shvetsov.day12;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/12">Day 12</a>
 */
public class Day12Inefficient {


    public static void main(String[] args) {

    }

    public long partOneAnton(List<String> inputList) {
        long res = 0L;
        List<Spring> springs = new ArrayList<>();
        inputList.forEach(s -> springs.add(new Spring(s)));

        for (Spring spring : springs) {
            res += IntStream.range(0, (int) Math.pow(2, spring.getUnknownCount()))
//                .mapToObj(i -> String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0'))
                    .mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), spring.getUnknownCount(), '0'))
                    .filter(s -> s.chars().filter(c -> c == '1').count() == spring.getBrokenTotal() - spring.getBrokenCount())
                    .map(s -> s.replaceAll("0", ".").replaceAll("1", "#"))
                    .map(s -> String.format(spring.getSpringDataForFormat(), ArrayUtils.toObject(s.toCharArray())))
                    .filter(s -> spring.getPattern().matcher(s).matches())
                    .count();
        }
        return res;
    }


    public BigInteger partTwoAnton(List<String> inputList) {
        BigInteger res = BigInteger.ZERO;
        List<Spring> springs = new ArrayList<>();
        inputList.forEach(s -> springs.add(new Spring(s)));

        for (Spring spring : springs) {
            long simpleArrangements = IntStream.range(0, (int) Math.pow(2, spring.getUnknownCount()))
//                .mapToObj(i -> String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0'))
                    .mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), spring.getUnknownCount(), '0'))
                    .filter(s -> s.chars().filter(c -> c == '1').count() == spring.getBrokenTotal() - spring.getBrokenCount())
                    .map(s -> s.replaceAll("0", ".").replaceAll("1", "#"))
                    .map(s -> String.format(spring.getSpringDataForFormat(), ArrayUtils.toObject(s.toCharArray())))
                    .filter(s -> spring.getPattern().matcher(s).matches())
                    .count();
            long advanceArrangements1 = simpleArrangements;
            long advanceArrangements2 = simpleArrangements;


            if (spring.isStartWithFirstGroup()) {
                advanceArrangements1 = IntStream.range(0, (int) Math.pow(2, spring.getAdvanceUnknownCount()))
//                .mapToObj(i -> String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0'))
                        .mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), spring.getAdvanceUnknownCount(), '0'))
                        .filter(s -> s.chars().filter(c -> c == '1').count() == spring.getBrokenTotal() - spring.getBrokenCount())
                        .map(s -> s.replaceAll("0", ".").replaceAll("1", "#"))
                        .map(s -> String.format(spring.getAdvanceSpringData1ForFormat(), ArrayUtils.toObject(s.toCharArray())))
                        .filter(s -> spring.getPattern().matcher(s).matches())
                        .count();
            }

            if (spring.isEndsWithLastGroup()) {
                advanceArrangements2 = IntStream.range(0, (int) Math.pow(2, spring.getAdvanceUnknownCount()))
//                .mapToObj(i -> String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0'))
                        .mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), spring.getAdvanceUnknownCount(), '0'))
                        .filter(s -> s.chars().filter(c -> c == '1').count() == spring.getBrokenTotal() - spring.getBrokenCount())
                        .map(s -> s.replaceAll("0", ".").replaceAll("1", "#"))
                        .map(s -> String.format(spring.getAdvanceSpringData2ForFormat(), ArrayUtils.toObject(s.toCharArray())))
                        .filter(s -> spring.getPattern().matcher(s).matches())
                        .count();
            }

            long advanceArrangements = Math.max(advanceArrangements1, advanceArrangements2);
            System.out.println(spring.springData + ": " + simpleArrangements + ", " + spring.advanceSpringData1 + ": " + advanceArrangements1 + ", " + spring.advanceSpringData2 + ": " + advanceArrangements2);
            res = res.add(BigInteger.valueOf(advanceArrangements).pow(4).multiply(BigInteger.valueOf(simpleArrangements)));
        }
        return res;
    }


    private class Spring {

        private final String springData;
        private final String advanceSpringData1;
        private final String advanceSpringData2;
        private final String brokenData;
        @Getter
        private final Pattern pattern;

        public Spring(String input) {
            this.springData = StringUtils.substringBefore(input, " ")/*.replaceAll("\\.", "0").replaceAll("#", "1")*/;
            this.advanceSpringData1 = this.springData + '?';
            this.advanceSpringData2 = '?' + this.springData;
            this.brokenData = StringUtils.substringAfter(input, " ");
            String regex = "\\.*"
                    + Arrays.stream(this.brokenData.split(","))
                    .map(s -> "#{" + s + "}")
                    .collect(Collectors.joining("\\.+"))
                    + "\\.*";
            this.pattern = Pattern.compile(regex);
        }

        private int getUnknownCount() {
            return (int) this.springData.chars().filter(c -> c == '?').count();
        }

        private int getAdvanceUnknownCount() {
            return (int) this.advanceSpringData1.chars().filter(c -> c == '?').count();
        }

        private int getBrokenCount() {
            return (int) this.springData.chars().filter(c -> c == '#').count();
        }

        private int getBrokenTotal() {
            return Arrays.stream(brokenData.split(","))
                    .mapToInt(Integer::parseInt)
                    .sum();
        }

        private String getSpringDataForFormat() {
            StringBuilder sb = new StringBuilder();
            int n = 1;
            for (int i = 0; i < this.springData.length(); i++) {
                if (this.springData.charAt(i) != '?') {
                    sb.append(this.springData.charAt(i));
                } else {
                    sb.append("%c");
                    n++;
                }
            }
            return sb.toString();
        }

        private String getAdvanceSpringData1ForFormat() {
            StringBuilder sb = new StringBuilder();
            int n = 1;
            for (int i = 0; i < this.advanceSpringData1.length(); i++) {
                if (this.advanceSpringData1.charAt(i) != '?') {
                    sb.append(this.advanceSpringData1.charAt(i));
                } else {
                    sb.append("%c");
                    n++;
                }
            }
            return sb.toString();
        }

        private String getAdvanceSpringData2ForFormat() {
            StringBuilder sb = new StringBuilder();
            int n = 1;
            for (int i = 0; i < this.advanceSpringData2.length(); i++) {
                if (this.advanceSpringData2.charAt(i) != '?') {
                    sb.append(this.advanceSpringData2.charAt(i));
                } else {
                    sb.append("%c");
                    n++;
                }
            }
            return sb.toString();
        }

        private boolean isEndsWithLastGroup() {
            List<Integer> groups = Arrays.stream(this.brokenData.split(",")).map(Integer::valueOf).toList();
            int lastGroup = groups.getLast();
            if (springData.endsWith(StringUtils.repeat('#', lastGroup))) {
                return false;
            }
            return true;
        }

        private boolean isStartWithFirstGroup() {
            List<Integer> groups = Arrays.stream(this.brokenData.split(",")).map(Integer::valueOf).toList();
            int lastGroup = groups.getFirst();
            if (springData.startsWith(StringUtils.repeat('#', lastGroup))) {
                return false;
            }
            return true;
        }
    }
}