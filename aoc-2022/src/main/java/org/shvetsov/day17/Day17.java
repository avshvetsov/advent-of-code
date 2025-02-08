package org.shvetsov.day17;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/17">Day 17</a>
 */
public class Day17 {

    public long partOne(List<String> input, int rocks) {
        final int CAVE_DEPTH = 5000;
        char[][] cave = new char[CAVE_DEPTH][9];
        for (int i = 0; i < cave.length; i++) {
            if (i == cave.length - 1) {
                Arrays.fill(cave[i], '#');
            } else {
                Arrays.fill(cave[i], '.');
                cave[i][0] = '#';
                cave[i][cave[i].length - 1] = '#';
            }
        }

        Shape[] shapes = Shape.values();
        char[] jets = input.getFirst().toCharArray();
        int minRow = CAVE_DEPTH - 1;
        int i = 0;
        int j = 0;
        while (i < rocks) {
            Shape shape = shapes[i % shapes.length];
            Rock rock = new Rock(minRow, shape);
            while (rock.canMove(cave, Direction.SOUTH)) {
                rock.moveIfCan(cave, Direction.SOUTH);
                Direction jet = jets[j++ % jets.length] == '<' ? Direction.WEST : Direction.EAST;
                rock.moveIfCan(cave, jet);
            }
            int minRowRock = Integer.MAX_VALUE;
            for (Point p : rock.getShape()) {
                cave[p.r()][p.c()] = '#';
                minRowRock = Math.min(minRowRock, p.r());
            }
            minRow = Math.min(minRow, minRowRock);
            i++;
        }
        return CAVE_DEPTH - 1 - minRow;
    }


    public long partTwo(List<String> input, long rocks) {

        return 0;
    }

}