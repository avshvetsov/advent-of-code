package org.shvetsov.day3;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <a href="https://adventofcode.com/2024/day/3">Day 3</a>
 */
public class Day3 {

    public long partOne(List<String> input) {
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            sb.append(s);
        }
        long result = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(sb.toString());
        while (matcher.find()) {
            String mul = matcher.group();
            result += Long.parseLong(StringUtils.substringBetween(mul, "mul(", ",")) * Integer.parseInt(StringUtils.substringBetween(mul, ",", ")"));
        }
        return result;
    }


    public long partTwo(List<String> input) {
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            sb.append(s);
        }
        long result = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(sb.toString());
        boolean enable = true;
        while (matcher.find()) {
            switch (matcher.group()) {
                case "do()" -> enable = true;
                case "don't()" -> enable = false;
                case String s when s.startsWith("mul") -> {
                    if (enable)
                        result += Long.parseLong(StringUtils.substringBetween(s, "mul(", ",")) * Integer.parseInt(StringUtils.substringBetween(s, ",", ")"));
                }
                default -> throw new IllegalStateException("Unexpected value");
            }
        }
        return result;
    }
}

