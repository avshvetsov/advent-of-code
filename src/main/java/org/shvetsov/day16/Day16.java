package org.shvetsov.day16;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Grid;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.Utils;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/16">Day 16</a>
 */
public class Day16 {

    public static void main(String[] args) {

    }

    public long partOneAnton(List<String> inputList) {
        Contraption contraption = Contraption.ofCharacter(inputList);
        Pair<Point, Direction> start = Pair.of(Point.of(0, 0), Direction.EAST);
        contraption.traceLight(start);
        return contraption.getEnergized().stream()
                .map(Pair::getLeft)
                .distinct()
                .count();
    }

    public long partTwoAnton(List<String> inputList) {
        Contraption contraption = Contraption.ofCharacter(inputList);

        List<Pair<Point, Direction>> startPoints = new ArrayList<>();
        Point point = Point.of(0, 0);
        do {
            startPoints.add(Pair.of(point, Direction.SOUTH));
            point = point.right();
        } while (contraption.isContainPoint(point));
        point = Point.of(0, contraption.sizeColumn() - 1);
        do {
            startPoints.add(Pair.of(point, Direction.WEST));
            point = point.down();
        } while (contraption.isContainPoint(point));
        point = Point.of(contraption.sizeRow() - 1, contraption.sizeColumn() - 1);
        do {
            startPoints.add(Pair.of(point, Direction.NORTH));
            point = point.left();
        } while (contraption.isContainPoint(point));
        point = Point.of(contraption.sizeRow() - 1, 0);
        do {
            startPoints.add(Pair.of(point, Direction.EAST));
            point = point.up();
        } while (contraption.isContainPoint(point));

        List<Long> results = new ArrayList<>();
        for (Pair<Point, Direction> start : startPoints) {
            contraption.clearRoute();
            contraption.traceLight(start);
            results.add(contraption.getEnergized().stream()
                    .map(Pair::getLeft)
                    .distinct()
                    .count());
        }

        return results.stream().max(Long::compareTo).orElse(0L);
    }

    @Getter
    public static class Contraption extends Grid<Character> {

        private final Set<Pair<Point, Direction>> energized = new HashSet<>();

        private static final Set<Character> MIRRORS = Set.of('/', '\\');
        private static final Set<Character> SPLITTERS = Set.of('|', '-');

        private Contraption(List<List<Character>> lists) {
            super(lists);
        }

        public static Contraption ofCharacter(List<String> lines) {
            return new Contraption(Utils.parseInputInCharacterGrid(lines));
        }

        public void clearRoute() {
            energized.clear();
        }

        public void traceLight(Pair<Point, Direction> pointDirection) {
            Point currentPoint = pointDirection.getLeft();
            Direction currentDirection = pointDirection.getRight();

            if (!isContainPoint(currentPoint)) {
                return;
            }
            if (energized.contains(pointDirection)) {
                return;
            } else {
                energized.add(pointDirection);
            }

            Character currentSign = this.getValue(pointDirection.getLeft());

            if (currentSign.equals('.')) {
                traceLight(Pair.of(currentPoint.move(currentDirection), currentDirection));
            } else if (MIRRORS.contains(currentSign)) {
                switch (currentDirection) {
                    case NORTH -> {
                        if (currentSign.equals('/')) {
                            traceLight(Pair.of(currentPoint.move(Direction.EAST), Direction.EAST));
                        } else if (currentSign.equals('\\')) {
                            traceLight(Pair.of(currentPoint.move(Direction.WEST), Direction.WEST));
                        }
                    }
                    case EAST -> {
                        if (currentSign.equals('/')) {
                            traceLight(Pair.of(currentPoint.move(Direction.NORTH), Direction.NORTH));
                        } else if (currentSign.equals('\\')) {
                            traceLight(Pair.of(currentPoint.move(Direction.SOUTH), Direction.SOUTH));
                        }
                    }
                    case SOUTH -> {
                        if (currentSign.equals('/')) {
                            traceLight(Pair.of(currentPoint.move(Direction.WEST), Direction.WEST));
                        } else if (currentSign.equals('\\')) {
                            traceLight(Pair.of(currentPoint.move(Direction.EAST), Direction.EAST));
                        }
                    }
                    case WEST -> {
                        if (currentSign.equals('/')) {
                            traceLight(Pair.of(currentPoint.move(Direction.SOUTH), Direction.SOUTH));
                        } else if (currentSign.equals('\\')) {
                            traceLight(Pair.of(currentPoint.move(Direction.NORTH), Direction.NORTH));
                        }
                    }
                }
            } else if (SPLITTERS.contains(currentSign)) {
                switch (currentDirection) {
                    case NORTH -> {
                        if (currentSign.equals('|')) {
                            traceLight(Pair.of(currentPoint.move(Direction.NORTH), Direction.NORTH));
                        } else if (currentSign.equals('-')) {
                            traceLight(Pair.of(currentPoint.move(Direction.WEST), Direction.WEST));
                            traceLight(Pair.of(currentPoint.move(Direction.EAST), Direction.EAST));
                        }
                    }
                    case EAST -> {
                        if (currentSign.equals('|')) {
                            traceLight(Pair.of(currentPoint.move(Direction.NORTH), Direction.NORTH));
                            traceLight(Pair.of(currentPoint.move(Direction.SOUTH), Direction.SOUTH));
                        } else if (currentSign.equals('-')) {
                            traceLight(Pair.of(currentPoint.move(Direction.EAST), Direction.EAST));
                        }
                    }
                    case SOUTH -> {
                        if (currentSign.equals('|')) {
                            traceLight(Pair.of(currentPoint.move(Direction.SOUTH), Direction.SOUTH));
                        } else if (currentSign.equals('-')) {
                            traceLight(Pair.of(currentPoint.move(Direction.EAST), Direction.EAST));
                            traceLight(Pair.of(currentPoint.move(Direction.WEST), Direction.WEST));
                        }
                    }
                    case WEST -> {
                        if (currentSign.equals('|')) {
                            traceLight(Pair.of(currentPoint.move(Direction.NORTH), Direction.NORTH));
                            traceLight(Pair.of(currentPoint.move(Direction.SOUTH), Direction.SOUTH));
                        } else if (currentSign.equals('-')) {
                            traceLight(Pair.of(currentPoint.move(Direction.WEST), Direction.WEST));
                        }
                    }
                }
            }
        }
    }
}