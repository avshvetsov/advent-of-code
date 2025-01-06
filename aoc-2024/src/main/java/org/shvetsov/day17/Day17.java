package org.shvetsov.day17;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/17">Day 17</a>
 */
public class Day17 {

    public String partOne(List<String> input) {
        long a = Long.parseLong(StringUtils.substringAfter(input.get(0), "Register A: "));
        long b = Long.parseLong(StringUtils.substringAfter(input.get(1), "Register B: "));
        long c = Long.parseLong(StringUtils.substringAfter(input.get(2), "Register C: "));
        String program = StringUtils.substringAfter(input.get(4), "Program: ");
        ThreeBitComputer computer = new ThreeBitComputer(a, b, c, program);
        computer.run();
        return computer.getOutput();
    }


    public long partTwo(List<String> input) {

        return 0;
    }

}