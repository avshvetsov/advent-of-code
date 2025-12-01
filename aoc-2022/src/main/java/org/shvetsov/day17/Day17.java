package org.shvetsov.day17;

import org.apache.commons.lang3.tuple.Pair;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2022/day/17">Day 17</a>
 */
public class Day17 {

    private static final int CAVE_WIDTH = 7;
    private static final int CAVE_DEPTH = 500;

    public long partOne(List<String> input, int rocks) {
        long height = 0;
        char[][] cave = new char[CAVE_DEPTH][CAVE_WIDTH];
        for (int i = 0; i < CAVE_DEPTH; i++) {
            Arrays.fill(cave[i], '.');
        }
        Arrays.fill(cave[CAVE_DEPTH - 1], '#');

        Shape[] shapes = Shape.values();
        char[] jets = input.getFirst().toCharArray();
        int minRow = CAVE_DEPTH - 1;
        int i = 0;
        int j = 0;
        while (i < rocks) {
            int shapeNum = i % shapes.length;
            Shape shape = shapes[shapeNum];
            Rock rock = new Rock(minRow, shape);
            while (rock.moveIfCan(cave, Direction.SOUTH)) {
                int jetNum = j++ % jets.length;
                Direction jet = jets[jetNum] == '<' ? Direction.WEST : Direction.EAST;
                rock.moveIfCan(cave, jet);
            }
            int minRowRock = Integer.MAX_VALUE;
            for (Point p : rock.getShape()) {
                cave[p.r()][p.c()] = '#';
                minRowRock = Math.min(minRowRock, p.r());
            }
            minRow = Math.min(minRow, minRowRock);

            int floor = findMaxReachable(cave, minRow);
            if (floor < CAVE_DEPTH - 2) {
                cave = burnBottomNRows(cave, CAVE_DEPTH - 2 - floor);
                height += CAVE_DEPTH - 2 - floor;
                minRow += CAVE_DEPTH - 2 - floor;
            }
            i++;
        }
        return height + CAVE_DEPTH - 1 - minRow;
    }

    private char[][] burnBottomNRows(char[][] cave, int n) {
        char[][] newCave = new char[CAVE_DEPTH][CAVE_WIDTH];
        for (int i = 0; i < CAVE_DEPTH; i++) {
            Arrays.fill(newCave[i], '.');
        }
        Arrays.fill(newCave[CAVE_DEPTH - 1], '#');
        System.arraycopy(cave, 0, newCave, n, CAVE_DEPTH - 1 - n);
        return newCave;
    }


    private int findMaxReachable(char[][] cave, int minRow) {
        int maxReachable = minRow - 1;
        Set<Point> reachable = new HashSet<>();
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < CAVE_WIDTH; i++) {
            reachable.add(new Point(minRow - 1, i));
            if (cave[minRow][i] != '#') {
                queue.add(new Point(minRow, i));
                reachable.add(new Point(minRow, i));
            }
        }
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (Direction direction : Direction.values()) {
                Point moved = current.move(direction);
                if (moved.isIndexExistInArray(cave) && cave[moved.r()][moved.c()] != '#' && !reachable.contains(moved)) {
                    reachable.add(moved);
                    queue.add(moved);
                }
            }
            maxReachable = Math.max(maxReachable, current.r());
        }
        return maxReachable;
    }


    public long partTwo(List<String> input, long rocks) {
        int height = 0;
        char[][] cave = new char[CAVE_DEPTH][CAVE_WIDTH];
        for (int i = 0; i < CAVE_DEPTH; i++) {
            Arrays.fill(cave[i], '.');
        }
        Arrays.fill(cave[CAVE_DEPTH - 1], '#');

        Shape[] shapes = Shape.values();
        char[] jets = input.getFirst().toCharArray();
        int minRow = CAVE_DEPTH - 1;
        int i = 0;
        int j = 0;
        Map<Key, Pair<Integer, Integer>> stateCache = new HashMap<>();
        Map<Integer, Integer> rockHeight = new HashMap<>();
        int burnedRows = 0;
        while (i < rocks) {
            Key key = new Key(Arrays.stream(cave).map(char[]::clone).toArray(char[][]::new), i % shapes.length, j % jets.length);
            if (stateCache.containsKey(key)) {
                Pair<Integer, Integer> rockNumHeight = stateCache.get(key);
                int start = rockNumHeight.getLeft();
                int startHeight = rockNumHeight.getRight();
                int cycle = i - rockNumHeight.getLeft();
                int cycleHeight = height - rockNumHeight.getRight();
                long fullCycleHeight = ((rocks - start) / cycle) * cycleHeight;
                int partCycleHeight = rockHeight.get((int) ((rocks - start) % cycle) + start) - startHeight;
                return startHeight + fullCycleHeight + partCycleHeight;
            } else {
                //noinspection SuspiciousNameCombination
                stateCache.put(key, Pair.of(i, height));
                rockHeight.put(i, height);
            }

            Shape shape = shapes[i % shapes.length];
            Rock rock = new Rock(minRow, shape);
            while (rock.moveIfCan(cave, Direction.SOUTH)) {
                Direction jet = jets[j++ % jets.length] == '<' ? Direction.WEST : Direction.EAST;
                rock.moveIfCan(cave, jet);
            }
            int minRowRock = Integer.MAX_VALUE;
            for (Point p : rock.getShape()) {
                cave[p.r()][p.c()] = '#';
                minRowRock = Math.min(minRowRock, p.r());
            }
            minRow = Math.min(minRow, minRowRock);

            int floor = findMaxReachable(cave, minRow);
            if (floor < CAVE_DEPTH - 2) {
                int rowsToBurn = CAVE_DEPTH - 2 - floor;
                cave = burnBottomNRows(cave, rowsToBurn);
                burnedRows += rowsToBurn;
                minRow += rowsToBurn;
            }

            height = burnedRows + CAVE_DEPTH - 1 - minRow;
            i++;
        }
        return height;
    }


    record Key(char[][] cave, int rock, int jet) {
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Key(char[][] cave1, int rock1, int jet1))) return false;
            return jet == jet1 && rock == rock1 && Arrays.deepEquals(cave, cave1);
        }

        @Override
        public int hashCode() {
            int result = Arrays.deepHashCode(cave);
            result = 31 * result + rock;
            result = 31 * result + jet;
            return result;
        }
    }

}