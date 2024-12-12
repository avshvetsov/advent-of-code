package org.shvetsov.day12;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2024/day/12">Day 12</a>
 */
public class Day12 {

    public long partOne(List<String> input) {
        long result = 0;
        char[][] garden = Utils.parseInputInTwoDimArray(input);
        for (int r = 0; r < garden.length; r++) {
            for (int c = 0; c < garden[r].length; c++) {
                result += calculatePricePart1(garden, new Point(r, c));
            }
        }
        return result;
    }

    private long calculatePricePart1(char[][] garden, Point start) {
        char type = garden[start.r()][start.c()];
        if (type == '#') {
            return 0;
        }
        Set<Point> area = new HashSet<>();
        int perimeter = 0;
        Set<Point> current = Set.of(start);
        while (!current.isEmpty()) {
            Set<Point> next = new HashSet<>();
            for (Point point : current) {
                area.add(point);
                for (Direction dir : Direction.values()) {
                    Point potentialNext = point.move(dir);
                    if (potentialNext.isIndexExistInArray(garden) && garden[potentialNext.r()][potentialNext.c()] == type) {
                        if (!area.contains(potentialNext)) {
                            next.add(potentialNext);
                        }
                    } else {
                        perimeter++;
                    }
                }
            }
            current = next;
        }
        for (Point visited : area) {
            garden[visited.r()][visited.c()] = '#';
        }
        return (long) area.size() * perimeter;
    }


    public long partTwo(List<String> input) {
        long result = 0;
        char[][] garden = Utils.parseInputInTwoDimArray(input);
        Set<Point> countedFigures = new HashSet<>();
        for (int r = 0; r < garden.length; r++) {
            for (int c = 0; c < garden[r].length; c++) {
                if (!countedFigures.contains(new Point(r, c))) {
                    Set<Point> figure = getFigureRecursively(garden, new Point(r, c), new HashSet<>());
                    countedFigures.addAll(figure);
                    long corners = calculateCorners(figure);
                    result += (long) figure.size() * corners;
                }
            }
        }
        return result;
    }

    private long calculateCorners(Set<Point> figure) {
        long result = 0;
        for (Point point : figure) {
            List<Point> neighbors = new ArrayList<>();
            for (Direction dir : Direction.values()) {
                Point next = point.move(dir);
                if (figure.contains(next)) {
                    neighbors.add(next);
                }
            }
            result += switch (neighbors.size()) {
                case 0 -> 4;
                case 1 -> 2;
                case 2 -> {
                    Point diff = neighbors.get(0).subtract(neighbors.get(1));
                    if (Math.abs(diff.r()) == 1 && Math.abs(diff.c()) == 1) {
                        if (figure.contains(new Point(neighbors.get(0).r(), neighbors.get(1).c())) && figure.contains(new Point(neighbors.get(1).r(), neighbors.get(0).c()))) {
                            yield 1;
                        } else {
                            yield 2;
                        }
                    } else {
                        yield 0;
                    }
                }
                case 3,4 -> {
                    int corners = 0;
                    for (int i = 0; i < neighbors.size(); i++) {
                        for (int j = i + 1; j < neighbors.size(); j++) {
                            Point diff = neighbors.get(i).subtract(neighbors.get(j));
                            if (Math.abs(diff.r()) == 1 && Math.abs(diff.c()) == 1) {
                                if (!figure.contains(new Point(neighbors.get(i).r(), neighbors.get(j).c())) || !figure.contains(new Point(neighbors.get(j).r(), neighbors.get(i).c()))) {
                                    corners += 1;
                                }
                            }
                        }
                    }
                    yield corners;
                }
                default -> 0;
            };
        }
        return result;
    }

    private Set<Point> getFigureRecursively(char[][] garden, Point current, Set<Point> visited) {
        visited.add(current);
        for (Direction dir : Direction.values()) {
            Point next = current.move(dir);
            if (next.isIndexExistInArray(garden) && !visited.contains(next) && garden[current.r()][current.c()] == garden[next.r()][next.c()]) {
                getFigureRecursively(garden, next, visited);
            }
        }
        return visited;
    }

}