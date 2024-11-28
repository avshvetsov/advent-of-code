package org.shvetsov.day12;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2022/day/12">Day 12</a>
 */
public class Day12 {

    public long partOne(List<String> input) {
        Triple<int[][], Point, Point> mapStartEnd = buildMap(input);
        return dijkstra(mapStartEnd.getLeft(), mapStartEnd.getMiddle(), mapStartEnd.getRight());
    }

    private Triple<int[][], Point, Point> buildMap(List<String> input) {
        int[][] map = new int[input.size()][input.getFirst().length()];
        Point start = null, end = null;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            char[] charArray = s.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                if (charArray[j] == 'S') {
                    start = Point.of(i, j);
                    map[i][j] = 0;
                } else if (charArray[j] == 'E') {
                    end = Point.of(i, j);
                    map[i][j] = 25;
                } else {
                    map[i][j] = charArray[j] - 'a';
                }
            }
        }
        return Triple.of(map, start, end);
    }

    private int dijkstra(int[][] map, Point start, Point end) {
        Set<Point> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<Pair<Integer, Point>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getLeft));
        pq.add(Pair.of(0, start));
        while (!pq.isEmpty()) {
            Pair<Integer, Point> current = pq.poll();
            Integer distance = current.getLeft();
            Point point = current.getRight();
            if (point.equals(end)) {
                return distance;
            }
            for (Direction direction : Direction.values()) {
                Point next = point.move(direction);
                if (!visited.contains(next) && next.isIndexExistInArray(map) && map[next.r][next.c] - map[point.r][point.c] < 2) {
                    visited.add(next);
                    pq.add(Pair.of(distance + 1, next));
                }
            }
        }
        return -1;
    }


    public long partTwo(List<String> input) {
        Triple<int[][], Point, Point> mapStartEnd = buildMap(input);
        int result = Integer.MAX_VALUE;
        int[][] map = mapStartEnd.getLeft();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    int dijkstra = dijkstra(map, Point.of(i, j), mapStartEnd.getRight());
                    if (dijkstra > 0) {
                        result = Math.min(result, dijkstra);
                    }
                }
            }
        }
        return result;
    }

}