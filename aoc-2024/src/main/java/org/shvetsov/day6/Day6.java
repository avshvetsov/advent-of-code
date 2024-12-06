package org.shvetsov.day6;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2024/day/6">Day 6</a>
 */
public class Day6 {

    public long partOne(List<String> input) {
        char[][] map = Utils.parseInputInTwoDimArray(input);
        Direction direction = Direction.NORTH;
        Point guard = findGuard(map);
        Set<Point> visited = findVisited(map, direction, guard);
        return visited.size();
    }

    private Set<Point> findVisited(char[][] map, Direction direction, Point guard) {
        Point next = guard.move(direction);
        Set<Point> visited = new HashSet<>();
        visited.add(guard);
        while (next.isIndexExistInArray(map)) {
            if (map[next.r()][next.c()] == '#') {
                direction = direction.turnRight();
                next = guard.move(direction);
            } else {
                guard = next;
                next = guard.move(direction);
                visited.add(guard);
            }
        }
        return visited;
    }

    private Point findGuard(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '^') {
                    return new Point(i, j);
                }
            }
        }
        throw new IllegalArgumentException("No guard found");
    }


    public long partTwo(List<String> input) {
        char[][] map = Utils.parseInputInTwoDimArray(input);
        Direction direction = Direction.NORTH;
        Point start = findGuard(map);
        Set<Point> visited = findVisited(map, direction, start);
        int result = 0;
        for (Point obstruction : visited) {
            if (!obstruction.equals(start)) {
                map[obstruction.r()][obstruction.c()] = '#';
                if (isGuardInTheLoop(map, start)) {
                    result++;
                }
                map[obstruction.r()][obstruction.c()] = '.';
            }
        }
        return result;
    }

    private boolean isGuardInTheLoop(char[][] map, Point start) {
        GuardDirection current = new GuardDirection(start, Direction.NORTH);
        Point next = start.move(current.direction);
        Set<GuardDirection> visited = new HashSet<>();
        visited.add(current);
        while (next.isIndexExistInArray(map)) {
            if (map[next.r()][next.c()] == '#') {
                current = new GuardDirection(current.guard, current.direction.turnRight());
                next = current.guard.move(current.direction);
            } else {
                current = new GuardDirection(next, current.direction);
                next = current.guard.move(current.direction);
            }
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
        }
        return false;
    }

    private record GuardDirection(Point guard, Direction direction) {}

}