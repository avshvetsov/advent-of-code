package org.shvetsov.day6;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/6">Day 6</a>
 */
public class Day6 {

    public long partOne(List<String> input) {
        int UNIQUE_COUNT = 4;
        return findLastIndexOfNUniqueCharacter(input.getFirst(), UNIQUE_COUNT);
    }

    private long findLastIndexOfNUniqueCharacter(String s, int UNIQUE_COUNT) {
        int l = 0;
        int r = 0;
        int[] lastSymbolIndex = new int[26];
        Arrays.fill(lastSymbolIndex, -1);
        while (r - l < UNIQUE_COUNT) {
            int charIndexR = s.charAt(r) - 'a';
            int lastR = lastSymbolIndex[charIndexR];
            if (r - lastR < UNIQUE_COUNT) {
                while (l <= lastR) {
                    l++;
                }
            }
            lastSymbolIndex[charIndexR] = r;
            r++;
        }
        return r;
    }


    public long partTwo(List<String> input) {
        int UNIQUE_COUNT = 14;
        return findLastIndexOfNUniqueCharacter(input.getFirst(), UNIQUE_COUNT);
    }

}