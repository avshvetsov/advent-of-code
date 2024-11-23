package org.shvetsov.day21;

import org.apache.commons.lang3.StringUtils;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * The row and the column where 'S'-point located, contains only plots ('.').
     * <br><img src=img.png/>
     * <br>So we can calculate counts of covered maps:
     * <br>- start map need 65 steps to other maps;
     * <br>- (26_501_365 - 65) / 131 = 202_300 maps in each direction;
     * <br>- maps size 131, so map switch plots which need to count (odd/even)
     * <br>- so, we have 202_300 * 202_300 maps with non-start count (even) and 202_299 * 202_299 maps with start count (odd)
     * <br>- plus we have to count cut corners map: 4 corner with start count + 202_299 * 4 big start + 202_300 * 4 small non-start
     */
    public long partTwoAnton(List<String> inputList, int steps) {
        long mapCount = (steps - 65) / 131;
        char[][] map = buildCharMap(inputList);
        int plotsInStart = countPlots(map, Point.of(65, 65), 131, false);
        int plotsInNonStart = countPlots(map, Point.of(65, 65), 131, true);

        int plotsLeftCorner = countPlots(map, Point.of(65, 130), 131, true);
        int plotsTopCorner = countPlots(map, Point.of(130, 65), 131, true);
        int plotsRightCorner = countPlots(map, Point.of(65, 0), 131, true);
        int plotsBottomCorner = countPlots(map, Point.of(0, 65), 131, true);
        int bigNWSide = countPlots(map, Point.of(130, 130), 131 + 65, false);
        int bigNESide = countPlots(map, Point.of(130, 0), 131 + 65, false);
        int bigSESide = countPlots(map, Point.of(0, 0), 131 + 65, false);
        int bigSWSide = countPlots(map, Point.of(0, 130), 131 + 65, false);
        int smallNWSide = countPlots(map, Point.of(130, 130), 65, true);
        int smallNESide = countPlots(map, Point.of(130, 0), 65, true);
        int smallSESide = countPlots(map, Point.of(0, 0), 65, true);
        int smallSWSide = countPlots(map, Point.of(0, 130), 65, true);
        return mapCount * mapCount * plotsInNonStart + (mapCount - 1) * (mapCount - 1) * plotsInStart +
                + plotsLeftCorner + plotsTopCorner + plotsRightCorner + plotsBottomCorner +
                + (mapCount - 1) * bigNWSide + (mapCount - 1) * bigNESide + (mapCount - 1) * bigSESide + (mapCount - 1) * bigSWSide +
                + mapCount * smallNWSide + mapCount * smallNESide + mapCount * smallSESide + mapCount * smallSWSide;
    }


    private int countPlots(char[][] originMap, Point start, int steps, boolean countStart) {
        char[][] map = copyArray(originMap);
        int result = 0;
        Set<Point> current = new HashSet<>();
        current.add(start);
        for (int i = 0; i < steps; i++) {
            if (countStart) result += current.size();
            Set<Point> next = new HashSet<>();
            for (Point point : current) {
                map[point.r][point.c] = '#';
                for (Direction dir : Direction.values()) {
                    Point potential = point.move(dir);
                    if (Utils.isIndexExistInArray(map, potential.r, potential.c) && (map[potential.r][potential.c] == '.' || map[potential.r][potential.c] == 'S')) {
                        next.add(potential);
                    }
                }
            }
            current = next;
            countStart ^= true;
        }
        return result;
    }

    private char[][] buildCharMap(List<String> inputList) {
        char[][] map = new char[inputList.size()][inputList.get(0).length()];
        for (int i = 0; i < inputList.size(); i++) {
            map[i] = inputList.get(i).toCharArray();
        }
        return map;
    }

    private char[][] copyArray(char[][] array) {
        char[][] newArray = new char[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            char[] row = array[i];
            newArray[i] = Arrays.copyOf(row, row.length);
        }
        return newArray;
    }

}