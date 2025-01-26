package org.shvetsov.day25;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/25">Day 25</a>
 */
public class Day25 {

    public long partOne(List<String> input) {
        List<int[]> locks = new ArrayList<>();
        List<int[]> keys = new ArrayList<>();
        int[] current = new int[5];
        int isLock = -1;
        for (String s : input) {
            if (s.isEmpty()) {
                if (isLock == 1) {
                    locks.add(current);
                } else {
                    keys.add(current);
                }
                current = new int[5];
                isLock = -1;
            } else {
                char[] pins = s.toCharArray();
                if (isLock == -1) {
                    isLock = pins[0] == '#' ? 1 : 0;
                }
                for (int i = 0; i < pins.length; i++) {
                    if (pins[i] == '#') {
                        current[i]++;
                    }
                }
            }
        }
        if (isLock == 1) {
            locks.add(current);
        } else {
            keys.add(current);
        }

        long result = 0;
        for (int[] lock : locks) {
            for (int[] key : keys) {
                boolean isFit = true;
                for (int i = 0; i < lock.length; i++) {
                    if (lock[i] + key[i] > 7) {
                        isFit = false;
                        break;
                    }
                }
                if (isFit) {
                    result++;
                }
            }
        }
        return result;
    }


}