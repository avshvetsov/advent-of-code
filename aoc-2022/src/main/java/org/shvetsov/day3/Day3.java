package org.shvetsov.day3;

import java.util.BitSet;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/3">Day 3</a>
 */
public class Day3 {

    public long partOne(List<String> input) {
        long result = 0;
        for (String s : input) {
            BitSet left = new BitSet();
            BitSet right = new BitSet();
            populateBitSet(s.substring(0, s.length() / 2), left);
            populateBitSet(s.substring(s.length() / 2), right);
            left.and(right);
            result += left.nextSetBit(0);
        }
        return result;
    }

    private int getPriority(char c) {
        if (Character.isLowerCase(c)) {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }


    public long partTwo(List<String> input) {
        long result = 0;
        BitSet first = new BitSet();
        BitSet second = new BitSet();
        BitSet third = new BitSet();
        for (int i = 1; i < input.size() + 1; i++) {
            if (i % 3 == 1) {
                populateBitSet(input.get(i - 1), first);
            } else if (i % 3 == 2) {
                populateBitSet(input.get(i - 1), second);
            } else {
                populateBitSet(input.get(i - 1), third);
                first.and(second);
                first.and(third);
                result += first.nextSetBit(0);
                first = new BitSet();
                second = new BitSet();
                third = new BitSet();
            }
        }
        return result;
    }

    private void populateBitSet(String input, BitSet bitSet) {
        for (char c : input.toCharArray()) {
            bitSet.set(getPriority(c));
        }
    }

}