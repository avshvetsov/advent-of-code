package org.shvetsov.day22;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/22">Day 22</a>
 */
public class Day22 {

    public long partOneAnton(List<String> inputList) {
        Map<Integer, List<int[]>> bricks = new HashMap<>();
        int maxX = 0, maxY = 0, maxZ = 0;
        for (int i = 0; i < inputList.size(); i++) {
            String s = inputList.get(i);
            int[] start = Arrays.stream(s.split("~")[0].split(",")).mapToInt(Integer::parseInt).toArray();
            int[] end = Arrays.stream(s.split("~")[1].split(",")).mapToInt(Integer::parseInt).toArray();
            for (int x = start[0]; x <= end[0]; x++) {
                for (int y = start[1]; y <= end[1]; y++) {
                    for (int z = start[2]; z <= end[2]; z++) {
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                        maxZ = Math.max(maxZ, z);
                        bricks.computeIfAbsent(i + 1, integer -> new ArrayList<>()).add(new int[]{x, y, z});
                    }
                }
            }
        }
        int[][][] space = new int[maxX + 1][maxY + 1][maxZ + 1];
        for (int x = 0; x < space.length; x++) {
            for (int y = 0; y < space[x].length; y++) {
                space[x][y][0] = -1;
            }
        }
        for (Map.Entry<Integer, List<int[]>> entry : bricks.entrySet()) {
            for (int[] coordinate : entry.getValue()) {
                space[coordinate[0]][coordinate[1]][coordinate[2]] = entry.getKey();
            }
        }

        //gravity
        for (int z = 2; z < maxZ + 1; z++) {
            Map<Integer, List<int[]>> coordinateOfParts = new HashMap<>();
            for (int x = 0; x < maxX + 1; x++) {
                for (int y = 0; y < maxY + 1; y++) {
                    if (space[x][y][z] > 0) {
                        coordinateOfParts.computeIfAbsent(space[x][y][z], integer -> new ArrayList<>()).add(new int[]{x, y, z});
                    }
                }
            }
            for (Map.Entry<Integer, List<int[]>> entry : coordinateOfParts.entrySet()) {
                List<int[]> brick = entry.getValue();
                boolean canMoveDown = true;
                while (canMoveDown) {
                    for (int[] coordinate : brick) {
                        canMoveDown &= space[coordinate[0]][coordinate[1]][coordinate[2] - 1] == 0;
                    }
                    if (canMoveDown) {
                        for (int[] coordinate : brick) {
                            space[coordinate[0]][coordinate[1]][coordinate[2]] = 0;
                            coordinate[2]--;
                            space[coordinate[0]][coordinate[1]][coordinate[2]] = entry.getKey();
                        }
                    }
                }
            }
        }

        //count disintegrate possibilities
        bricks = new HashMap<>();
        for (int x = 0; x < maxX + 1; x++) {
            for (int y = 0; y < maxY + 1; y++) {
                for (int z = 0; z < maxZ + 1; z++) {
                    if (space[x][y][z] > 0) {
                        bricks.computeIfAbsent(space[x][y][z], integer -> new ArrayList<>()).add(new int[]{x, y, z});
                    }
                }
            }
        }
        Map<Integer, Pair<Set<Integer>, Set<Integer>>> aboveUnderMap = new HashMap<>();
        for (Map.Entry<Integer, List<int[]>> brick : bricks.entrySet()) {
            Set<Integer> above = new HashSet<>();
            Set<Integer> under = new HashSet<>();
            for (int[] coordinate : brick.getValue()) {
                if (coordinate[2] < maxZ) {
                    if (space[coordinate[0]][coordinate[1]][coordinate[2] + 1] > 0 && space[coordinate[0]][coordinate[1]][coordinate[2] + 1] != brick.getKey()) {
                        under.add(space[coordinate[0]][coordinate[1]][coordinate[2] + 1]);
                    }
                }
                if (coordinate[2] > 0) {
                    if (space[coordinate[0]][coordinate[1]][coordinate[2] - 1] > 0 && space[coordinate[0]][coordinate[1]][coordinate[2] - 1] != brick.getKey()) {
                        above.add(space[coordinate[0]][coordinate[1]][coordinate[2] - 1]);
                    }
                }
            }
            aboveUnderMap.put(brick.getKey(), new Pair<>(above, under));
        }

        int result = 0;
        for (Map.Entry<Integer, Pair<Set<Integer>, Set<Integer>>> entry : aboveUnderMap.entrySet()) {
            if (entry.getValue().getSecond().isEmpty()) {
                result++;
            } else if (entry.getValue().getSecond().stream().allMatch(n -> aboveUnderMap.get(n).getFirst().size() > 1)) {
                result++;
            }
        }
        return result;
    }



    public long partTwoAnton(List<String> inputList) {

        return 0;
    }

}