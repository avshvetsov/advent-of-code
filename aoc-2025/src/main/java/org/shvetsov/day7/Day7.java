package org.shvetsov.day7;

import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/7">Day 7</a>
 */
public class Day7 {

    public long partOne(List<String> input) {
        char[][] manifold = Utils.parseInputInTwoDimArray(input);
        PriorityQueue<Point> queue = new PriorityQueue<>();
        Set<Point> visited = new HashSet<>();
        for (int i = 0; i < manifold[0].length; i++) {
            if (manifold[0][i] == 'S') {
                queue.add(new Point(1, i));
                visited.add(new Point(1, i));
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            char sign = manifold[current.r()][current.c()];
            if (sign == '.') {
                Point down = current.down();
                if (down.isIndexExistInArray(manifold) && !visited.contains(down)) {
                    queue.add(current.down());
                    visited.add(current.down());
                }
            } else if (sign == '^') {
                Point left = current.left();
                if (left.isIndexExistInArray(manifold) && !visited.contains(left)) {
                    queue.add(left);
                    visited.add(left);
                }
                Point right = current.right();
                if (left.isIndexExistInArray(manifold) && !visited.contains(right)) {
                    queue.add(right);
                    visited.add(right);
                }
                result++;
            }
        }
        return result;
    }

    public long partTwo(List<String> input) {
        char[][] manifold = Utils.parseInputInTwoDimArray(input);
        for (int i = 0; i < manifold[0].length; i++) {
            if (manifold[0][i] == 'S') {
                return dfs(manifold, new Point(1, i));
            }
        }
        throw new IllegalStateException("No start point found");
    }

    Map<Point, Long> cache = new HashMap<>();

    private long dfs(char[][] manifold, Point point) {
        if (cache.containsKey(point)) {
            return cache.get(point);
        }
        if (!point.isIndexExistInArray(manifold)) {
            cache.put(point, 1L);
            return 1;
        }
        if (manifold[point.r()][point.c()] == '.') {
            long result = dfs(manifold, point.down());
            cache.put(point, result);
            return result;
        } else if (manifold[point.r()][point.c()] == '^') {
            long result = dfs(manifold, point.left()) + dfs(manifold, point.right());
            cache.put(point, result);
            return result;
        }
        throw new IllegalStateException("Unexpected value: " + point);
    }

}
