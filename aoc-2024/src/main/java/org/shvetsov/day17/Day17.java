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
        //Interesting JVM optimization
//        ThreeBitComputer computer = null;
//        for (int i = 0; i < 100; i++) {
        ThreeBitComputer computer = new ThreeBitComputer(a, b, c, program);
        computer.run();
//        }
        return computer.getOutput();
    }


    public long partTwo(List<String> input) {
        String program = StringUtils.substringAfter(input.get(4), "Program: ");
        int lastIndex = program.split(",").length - 1;
        long minAValue = 1L << (lastIndex * 3);
        long a = findA(program, lastIndex, minAValue);
        ThreeBitComputer computer = new ThreeBitComputer(a + minAValue, 0, 0, program);
        computer.run();
        if (!computer.getOutput().equals(program)) {
            throw new RuntimeException("Program does not match itself");
        }
        return a + minAValue;
    }

    private long findA(String program, int targetOutputIndex, long start) {
        if (targetOutputIndex < 0) {
            return 0;
        }
        long step = 1L << (targetOutputIndex * 3);
        for (long a = 0; a < 8 * step; a += step) {
            ThreeBitComputer computer = new ThreeBitComputer(a + start, 0, 0, program);
            computer.run();
            if (computer.output.size() > targetOutputIndex && computer.output.get(targetOutputIndex).equals(computer.program[targetOutputIndex])) {
                long next = findA(program, targetOutputIndex - 1, a + start);
                if (next != -1) {
                    return a + next;
                }
            }
        }
        return -1;
    }

}