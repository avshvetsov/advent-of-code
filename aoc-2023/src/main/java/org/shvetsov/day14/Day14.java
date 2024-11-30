package org.shvetsov.day14;

import lombok.EqualsAndHashCode;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Grid;
import org.shvetsov.utils.PointOld;
import org.shvetsov.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/14">Day 14</a>
 */
public class Day14 {

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> inputList) {
        Platform platform = Platform.ofCharacter(inputList);
        platform.tilting(Direction.NORTH);
//        platform.draw(String::valueOf);
        int rowsCount = platform.sizeRow();
        return IntStream.range(0, rowsCount)
                .map(i -> (int) platform.getRow(i).stream().filter(ch -> ch == 'O').count() * (rowsCount - i))
                .sum();
    }

    public static final int CYCLES = 1_000_000_000;

    public int partTwoAnton(List<String> inputList) {
        Platform platform = Platform.ofCharacter(inputList);
        int circle = 0;
        Map<Platform, Integer> map = new HashMap<>();
        map.put(new Platform(platform.getElementsCopy()), circle);
        boolean condition = true;
        do {
            platform.tilting(Direction.NORTH);
            platform.tilting(Direction.WEST);
            platform.tilting(Direction.SOUTH);
            platform.tilting(Direction.EAST);
            circle++;
            if (!map.containsKey(platform)) {
                map.put(new Platform(platform.getElementsCopy()), circle);
            } else {
                condition = false;
            }
        } while (condition);

        int loopStart = map.get(platform);
        int loopSize = circle - loopStart;
        int resLoopPos = loopStart + ((CYCLES - loopStart) % loopSize);
        Platform resPlatform = map.entrySet().stream()
                .filter(entry -> entry.getValue().equals(resLoopPos))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        int rowsCount = Objects.requireNonNull(resPlatform).sizeRow();
        return IntStream.range(0, rowsCount)
                .map(i -> (int) resPlatform.getRow(i).stream().filter(ch -> ch == 'O').count() * (rowsCount - i))
                .sum();
    }


    @EqualsAndHashCode(callSuper = true)
    public static class Platform extends Grid<Character> {

        private Platform(List<List<Character>> lists) {
            super(lists);
        }

        public static Platform ofCharacter(List<String> lines) {
            return new Platform(Utils.parseInputInCharacterGrid(lines));
        }

        public void tilting(Direction direction) {
            switch (direction) {
                case NORTH -> {
                    for (int i = 0; i < sizeColumn(); i++) {
                        int lastWall = -1;
                        for (int j = 0; j < sizeRow(); j++) {
                            switch (getValue(j, i)) {
                                case 'O' -> {
                                    swap(PointOld.of(j, i), PointOld.of(lastWall + 1, i));
                                    lastWall++;
                                }
                                case '#' -> lastWall = j;
                            }
                        }
                    }
                }
                case WEST -> {
                    for (int i = 0; i < sizeRow(); i++) {
                        int lastWall = -1;
                        for (int j = 0; j < sizeColumn(); j++) {
                            switch (getValue(i, j)) {
                                case 'O' -> {
                                    swap(PointOld.of(i, j), PointOld.of(i, lastWall + 1));
                                    lastWall++;
                                }
                                case '#' -> lastWall = j;
                            }
                        }
                    }
                }
                case SOUTH -> {
                    for (int i = 0; i < sizeColumn(); i++) {
                        int lastWall = sizeRow();
                        for (int j = sizeRow() - 1; j >= 0; j--) {
                            switch (getValue(j, i)) {
                                case 'O' -> {
                                    swap(PointOld.of(j, i), PointOld.of(lastWall - 1, i));
                                    lastWall--;
                                }
                                case '#' -> lastWall = j;
                            }
                        }
                    }
                }
                case EAST -> {
                    for (int i = 0; i < sizeRow(); i++) {
                        int lastWall = sizeColumn();
                        for (int j = sizeColumn() - 1; j >= 0; j--) {
                            switch (getValue(i, j)) {
                                case 'O' -> {
                                    swap(PointOld.of(i, j), PointOld.of(i, lastWall - 1));
                                    lastWall--;
                                }
                                case '#' -> lastWall = j;
                            }
                        }
                    }
                }
            }
        }
    }


}