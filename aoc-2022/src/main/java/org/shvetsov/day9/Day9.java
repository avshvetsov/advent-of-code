package org.shvetsov.day9;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2022/day/9">Day 9</a>
 */
public class Day9 {

    public long partOne(List<String> input) {
        Point head = Point.of(0, 0);
        Point tail = Point.of(0, 0);
        Set<Point> tailPositions = new HashSet<>();
        tailPositions.add(tail);
        for (String s : input) {
            String[] split = s.split(" ");
            Direction direction = directionOf(split[0]);
            int x = Integer.parseInt(split[1]);
            while (x > 0) {
                head.moveThis(direction);
                Point diff = head.subtract(tail);
                if (Math.abs(diff.r) > 1 || Math.abs(diff.c) > 1) {
                    diff.r = Math.abs(diff.r) < 2 ? 0 : diff.r / 2;
                    diff.c = Math.abs(diff.c) < 2 ? 0 : diff.c / 2;
                    tail = head.subtract(diff);
                    tailPositions.add(tail);
                }
                x--;
            }
        }
        return tailPositions.size();
    }


    private Direction directionOf(String s) {
        return switch (s) {
            case "U" -> Direction.NORTH;
            case "D" -> Direction.SOUTH;
            case "R" -> Direction.EAST;
            case "L" -> Direction.WEST;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }


    public long partTwo(List<String> input) {
        List<Point> rope = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rope.add(Point.of(0, 0));
        }
        Set<Point> tailPositions = new HashSet<>();
        tailPositions.add(rope.getLast());
        for (String s : input) {
            String[] split = s.split(" ");
            Direction direction = directionOf(split[0]);
            int x = Integer.parseInt(split[1]);
            while (x > 0) {
                int i = 1;
                rope.getFirst().moveThis(direction);
                while (i < rope.size()) {
                    Point diff = rope.get(i - 1).subtract(rope.get(i));
                    if (Math.abs(diff.r) > 1 || Math.abs(diff.c) > 1) {
                        diff.r = Math.abs(diff.r) < 2 ? 0 : diff.r / 2;
                        diff.c = Math.abs(diff.c) < 2 ? 0 : diff.c / 2;
                        rope.set(i, rope.get(i - 1).subtract(diff));
                        i++;
                    } else break;
                }
                tailPositions.add(rope.getLast());
                x--;
            }
        }
        return tailPositions.size();
    }

}