package org.shvetsov.day14;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/14">Day 14</a>
 */
public class Day14 {

    public long partOne(List<String> input) {
        char[][] cave = new char[1000][1000];
        for (char[] row : cave) {
            Arrays.fill(row, '.');
        }
        for (String s : input) {
            addRockToCave(cave, s);
        }
        long sandCount = 0;
        while (true) {
            Point position = dropSand(cave, new Point(0, 500));
            if (position.isIndexExistInArray(cave)) {
                cave[position.r()][position.c()] = 'o';
                sandCount++;
            } else {
                break;
            }
        }
        return sandCount;
    }

    private Point dropSand(char[][] cave, Point start) {
        Point current = start;
        while (current.isIndexExistInArray(cave)) {
            if (current.r() + 1 >= cave.length) {
                current = current.withR(current.r() + 1);
            } else if (cave[current.r() + 1][current.c()] == '.') {
                current = current.withR(current.r() + 1);
            } else if (cave[current.r() + 1][current.c() - 1] == '.') {
                current = new Point(current.r() + 1, current.c() - 1);
            } else if (cave[current.r() + 1][current.c() + 1] == '.') {
                current = new Point(current.r() + 1, current.c() + 1);
            } else {
                break;
            }
        }
        return current;
    }

    private void addRockToCave(char[][] cave, String s) {
        List<Point> points = Arrays.stream(s.split(" -> "))
                .map(pointS -> {
                    String[] xy = pointS.split(",");
                    return new Point(Integer.parseInt(xy[1]), Integer.parseInt(xy[0]));
                })
                .toList();
        for (int i = 0; i < points.size() - 1; i++) {
            Point point = points.get(i);
            Point nextPoint = points.get(i + 1);
            Point diff = point.subtract(nextPoint);
            Direction direction = switch (diff) {
                case Point(int r, int c) when r > 0 && c == 0 -> Direction.NORTH;
                case Point(int r, int c) when r < 0 && c == 0 -> Direction.SOUTH;
                case Point(int r, int c) when r == 0 && c > 0 -> Direction.WEST;
                case Point(int r, int c) when r == 0 && c < 0 -> Direction.EAST;
                default -> throw new IllegalStateException("Unexpected difference value: " + diff);
            };
            while (point.distance(nextPoint) > 0) {
                cave[point.r()][point.c()] = '#';
                point = point.move(direction);
            }
        }
        cave[points.getLast().r()][points.getLast().c()] = '#';
    }


    public long partTwo(List<String> input) {

        return 0;
    }

}