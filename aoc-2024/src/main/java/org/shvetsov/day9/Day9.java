package org.shvetsov.day9;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2024/day/9">Day 9</a>
 */
public class Day9 {

    public long partOne(List<String> input) {
        long result = 0;
        List<Integer> diskBlocks = getDiskBlocks(input);
        int l = 0, r = diskBlocks.size() - 1;
        while (l <= r) {
            if (diskBlocks.get(l) >= 0) {
                result += diskBlocks.get(l) * l;
            } else {
                result += diskBlocks.get(r) * l;
                do {
                    r--;
                } while (diskBlocks.get(r) < 0);
            }
            l++;
        }
        return result;
    }

    private List<Integer> getDiskBlocks(List<String> input) {
        List<Integer> diskBlocks = new ArrayList<>();
        char[] disk = input.getFirst().toCharArray();
        boolean isFree = false;
        int i = 0;
        int f = -1;
        for (char c : disk) {
            for (int j = 0; j < c - '0'; j++) {
                if (!isFree) {
                    diskBlocks.add(i);
                } else {
                    diskBlocks.add(f);
                }
            }
            if (isFree) {
                i++;
            }
            isFree = !isFree;
        }
        return diskBlocks;
    }


    public long partTwo(List<String> input) {
        long result = 0;
        List<Integer> diskBlocks = getDiskBlocks(input);
        diskBlocks = defragmentation(diskBlocks);
        for (int i = 0; i < diskBlocks.size(); i++) {
            if (diskBlocks.get(i) >= 0) {
                result += diskBlocks.get(i) * i;
            }
        }
        return result;
    }

    private List<Integer> defragmentation(List<Integer> diskBlocks) {
        List<Integer> result = new ArrayList<>(diskBlocks);
        //calculate emptySpaceSetsBySize
        List<TreeSet<Integer>> emptySpaceBySize = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            emptySpaceBySize.add(new TreeSet<>());
        }
        int i = 0;
        while (i < diskBlocks.size()) {
            if (diskBlocks.get(i) >= 0) {
                i++;
            } else {
                int j = i;
                while (diskBlocks.get(j) < 0) {
                    j++;
                }
                emptySpaceBySize.get(j - i).add(i);
                i = j;
            }
        }
        //make defragmentation
        int ii = diskBlocks.size() - 1;
        while (ii >= 0) {
            Integer value = diskBlocks.get(ii);
            if (value >= 0) {
                int size = findBlockSize(diskBlocks, ii);
                PositionBlockSize minPositionBlockSize = getMinPositionBlockSize(emptySpaceBySize, size);
                if (minPositionBlockSize != null && minPositionBlockSize.position() < ii) {
                    Integer newPosition = minPositionBlockSize.position();
                    Integer emptyBlockSize = minPositionBlockSize.size();
                    while (diskBlocks.get(ii).equals(value)) {
                        result.set(newPosition, value);
                        result.set(ii, -1);
                        newPosition++;
                        ii--;
                    }
                    emptySpaceBySize.get(emptyBlockSize).removeFirst();
                    emptySpaceBySize.get(emptyBlockSize - size).add(newPosition);
                } else {
                    ii -= size;
                }
            } else {
                ii--;
            }
        }
        return result;
    }

    private PositionBlockSize getMinPositionBlockSize(List<TreeSet<Integer>> emptySpaceBySize, int size) {
        return IntStream.range(size, 10)
                .mapToObj(index -> Pair.of(emptySpaceBySize.get(index), index))
                .filter(pair -> !pair.getLeft().isEmpty())
                .map(pair -> new PositionBlockSize(pair.getLeft().getFirst(), pair.getRight()))
                .min(Comparator.comparingInt(PositionBlockSize::position))
                .orElse(null);
    }

    private int findBlockSize(List<Integer> diskBlocks, int ii) {
        int size = 0;
        int value = diskBlocks.get(ii);
        do {
            size++;
            ii--;
        } while (ii >= 0 && diskBlocks.get(ii) == value);
        return size;
    }

    private record PositionBlockSize(Integer position, Integer size) {}

}