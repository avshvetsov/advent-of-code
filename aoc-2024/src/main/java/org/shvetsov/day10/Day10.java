package org.shvetsov.day10;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2024/day/10">Day 10</a>
 */
public class Day10 {

    private static final char END = '9';

    public long partOne(List<String> input) {
        long result = 0;
        char[][] map = Utils.parseInputInTwoDimArray(input);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '0') {
                    result += trailheadScorePart1(map, new Point(i, j), new HashSet<>());
                }
            }
        }
        return result;
    }

    private long trailheadScorePart1(char[][] map, Point point, Set<Point> visitedEnds) {
        long result = 0;
        char height = map[point.r()][point.c()];
        if (height == END) {
            if (!visitedEnds.contains(point)) {
                visitedEnds.add(point);
                return 1;
            } else {
                return 0;
            }
        }
        for (Direction dir : Direction.values()) {
            Point next = point.move(dir);
            if (next.isIndexExistInArray(map) && map[next.r()][next.c()] - height == 1) {
                result += trailheadScorePart1(map, next, visitedEnds);
            }
        }
        return result;
    }


    public long partTwo(List<String> input) {
        long result = 0;
        char[][] map = Utils.parseInputInTwoDimArray(input);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '0') {
                    result += trailheadScorePart2(map, new Point(i, j));
                }
            }
        }
        return result;
    }

    private long trailheadScorePart2(char[][] map, Point point) {
        long result = 0;
        char height = map[point.r()][point.c()];
        if (height == END) {
            return 1;
        }
        for (Direction dir : Direction.values()) {
            Point next = point.move(dir);
            if (next.isIndexExistInArray(map) && map[next.r()][next.c()] - height == 1) {
                result += trailheadScorePart2(map, next);
            }
        }
        return result;
    }

}