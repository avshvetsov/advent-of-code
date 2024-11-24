package org.shvetsov.day4;

import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/4">Day 4</a>
 */
public class Day4 {

    public long partOne(List<String> input) {
        int result = 0;
        for (String s : input) {
            String[] pair = s.split(",");
            Elf elf1 = new Elf(pair[0]);
            Elf elf2 = new Elf(pair[1]);
            if (elf1.contains(elf2) || elf2.contains(elf1)) result++;
        }
        return result;
    }


    public long partTwo(List<String> input) {
        int result = 0;
        for (String s : input) {
            String[] pair = s.split(",");
            Elf elf1 = new Elf(pair[0]);
            Elf elf2 = new Elf(pair[1]);
            if (elf1.overlaps(elf2)) result++;
        }
        return result;
    }

}