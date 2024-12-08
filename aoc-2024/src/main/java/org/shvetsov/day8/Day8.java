package org.shvetsov.day8;

import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2024/day/8">Day 8</a>
 */
public class Day8 {

    public long partOne(List<String> input) {
        char[][] map = Utils.parseInputInTwoDimArray(input);
        Map<Character, List<Point>> antennas = findAntennas(map);
        Set<Point> antinodes = new HashSet<>();
        for (List<Point> group : antennas.values()) {
            for (int i = 0; i < group.size(); i++) {
                for (int j = i + 1; j < group.size(); j++) {
                    for (Point antinode : calculateAntinodes(group.get(i), group.get(j))) {
                        if (antinode.isIndexExistInArray(map)) {
                            antinodes.add(antinode);
                        }
                    }
                }
            }
        }
        return antinodes.size();
    }

    private Map<Character, List<Point>> findAntennas(char[][] map) {
        Map<Character, List<Point>> antennas = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char c = map[i][j];
                if (c != '.') {
                    antennas.computeIfAbsent(c, k -> new ArrayList<>()).add(new Point(i, j));
                }
            }
        }
        return antennas;
    }

    private List<Point> calculateAntinodes(Point antenna1, Point antenna2) {
        List<Point> antinodes = new ArrayList<>();
        antinodes.add(antenna1.add(antenna1.subtract(antenna2)));
        antinodes.add(antenna2.add(antenna2.subtract(antenna1)));
        return antinodes;
    }


    public long partTwo(List<String> input) {
        char[][] map = Utils.parseInputInTwoDimArray(input);
        Map<Character, List<Point>> antennas = findAntennas(map);
        Set<Point> antinodes = new HashSet<>();
        for (List<Point> group : antennas.values()) {
            for (int i = 0; i < group.size(); i++) {
                for (int j = i + 1; j < group.size(); j++) {
                    antinodes.addAll(calculateAntinodesInBoundOfMap(map, group.get(i), group.get(j)));
                }
            }
        }
        return antinodes.size();
    }

    private List<Point> calculateAntinodesInBoundOfMap(char[][] map, Point antenna1, Point antenna2) {
        List<Point> antinodes = new ArrayList<>();
        Point antinodeMain = antenna1;
        Point antinodePrev = antenna2;
        while (antinodeMain.isIndexExistInArray(map)) {
            antinodes.add(antinodeMain);
            Point temp = antinodeMain;
            antinodeMain = antinodeMain.add(antinodeMain.subtract(antinodePrev));
            antinodePrev = temp;
        }

        antinodeMain = antenna2;
        antinodePrev = antenna1;
        while (antinodeMain.isIndexExistInArray(map)) {
            antinodes.add(antinodeMain);
            Point temp = antinodeMain;
            antinodeMain = antinodeMain.add(antinodeMain.subtract(antinodePrev));
            antinodePrev = temp;
        }
        return antinodes;
    }

}