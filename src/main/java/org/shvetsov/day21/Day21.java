package org.shvetsov.day21;

import org.apache.commons.lang3.StringUtils;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/21">Day 21</a>
 */
public class Day21 {

    public long partOneAnton(List<String> inputList, int steps) {
        String[][] map = buildMap(inputList);
        Set<Point> current = new HashSet<>();
        current.add(findSymbol(map, "S"));
        for (int i = 0; i <= steps; i++) {
            Set<Point> next = new HashSet<>();
            for (Point point : current) {
                map[point.r][point.c] = String.valueOf(i);
                next.addAll(expand(map, point));
            }
            current = next;
        }
        int result = 0;
        int rem = steps % 2;
        for (String[] row : map) {
            for (String value : row) {
                if (StringUtils.isNumeric(value) && Integer.parseInt(value) % 2 == rem) {
                    result++;
                }
            }
        }
        return result;
    }

    private Set<Point> expand(String[][] map, Point point) {
        Set<Point> next = new HashSet<>();
        for (Direction direction : Direction.values()) {
            Point potential = point.move(direction);
            if (potential.isIndexExistInArray(map) && map[potential.r][potential.c].equals(".")) {
                next.add(potential);
            }
        }
        return next;
    }

    private Point findSymbol(String[][] map, String target) {
        for (int i = 0; i < map.length; i++) {
            String[] row = map[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j].equals(target)) return Point.of(i, j);
            }
        }
        throw new IllegalStateException("Symbol not find");
    }

    private String[][] buildMap(List<String> inputList) {
        String[][] map = new String[inputList.size()][inputList.get(0).length()];
        for (int i = 0; i < inputList.size(); i++) {
            map[i] = inputList.get(i).split("");
        }
        return map;
    }


    public long partTwoAnton(List<String> inputList) {

        return 0;
    }

}