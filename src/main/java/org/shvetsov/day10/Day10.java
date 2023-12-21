package org.shvetsov.day10;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.shvetsov.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * <a href="https://adventofcode.com/2023/day/10">Day 10</a>
 */
public class Day10 {


    public static final char START_SIGN = 'S';

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> pipeMapString) {
        int res = 0;
        PipeMap map = new PipeMap(pipeMapString);
        char sign = START_SIGN;
        Coordinate current = map.getStartPoint(sign);
        Direction direction = map.getStartDirection(current);
        do {
            current = direction.move(current);
            sign = map.getTile(current);
            direction = direction.opposite(sign);
            res++;
        } while (map.getTile(current) != START_SIGN);
        return res / 2;
    }


    public int partTwoAnton(List<String> pipeMapString) {
        int res = 0;
        PipeMap pipeMap = new PipeMap(pipeMapString);
        Route route = new Route(pipeMap);

        char[][] map = pipeMap.map;
        Map<Coordinate, Character> routeMap = route.getRouteMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Coordinate checking = new Coordinate(i, j);
                if (routeMap.get(checking) == null) {
                    int crossed = 0;

                    Coordinate trace = checking.up();
                    while (trace.isInBorderOf(map)) {
                        Character traceChar = routeMap.get(trace);
                        if (traceChar != null) {
                            if (traceChar == '-' || traceChar == 'L' || traceChar == 'F') {
                                crossed++;
                            }
                        }
                        trace.upThis();
                    }
                    if (crossed % 2 == 1) {
                        res++;
                    }
                }
            }
        }


        return res;
    }

    @Getter
    private class PipeMap {
        private final char[][] map;

        public PipeMap(List<String> pipeMapString) {
            this.map = Utils.parseInputInTwoDimArray(pipeMapString);
        }

        public Coordinate getStartPoint(char startSign) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == startSign) {
                        return new Coordinate(i, j);
                    }
                }
            }
            throw new IllegalArgumentException();
        }

        public char getTile(Coordinate xy) {
            return map[xy.row][xy.column];
        }

        public Direction getStartDirection(Coordinate start) {
            if (Utils.isIndexExistInArray(map, start.row - 1, start.column)) {
                if (Direction.NORTH.opposite(map[start.row - 1][start.column]) != null) {
                    return Direction.NORTH;
                }
            }
            if (Utils.isIndexExistInArray(map, start.row + 1, start.column)) {
                if (Direction.SOUTH.opposite(map[start.row + 1][start.column]) != null) {
                    return Direction.SOUTH;
                }
            }
            if (Utils.isIndexExistInArray(map, start.row, start.column + 1)) {
                if (Direction.EAST.opposite(map[start.row][start.column + 1]) != null) {
                    return Direction.EAST;
                }
            }
            if (Utils.isIndexExistInArray(map, start.row, start.column - 1)) {
                if (Direction.WEST.opposite(map[start.row][start.column - 1]) != null) {
                    return Direction.WEST;
                }
            }
            throw new IllegalArgumentException();
        }

        public Direction getSDirection(Coordinate start) {
            boolean isSecond = false;
            if (Utils.isIndexExistInArray(map, start.row - 1, start.column)) {
                if (Direction.NORTH.opposite(map[start.row - 1][start.column]) != null) {
                    if (isSecond) {
                        return Direction.NORTH;
                    } else isSecond = true;
                }
            }
            if (Utils.isIndexExistInArray(map, start.row + 1, start.column)) {
                if (Direction.SOUTH.opposite(map[start.row + 1][start.column]) != null) {
                    if (isSecond) {
                        return Direction.SOUTH;
                    } else isSecond = true;
                }
            }
            if (Utils.isIndexExistInArray(map, start.row, start.column + 1)) {
                if (Direction.EAST.opposite(map[start.row][start.column + 1]) != null) {
                    if (isSecond) {
                        return Direction.EAST;
                    } else isSecond = true;
                }
            }
            if (Utils.isIndexExistInArray(map, start.row, start.column - 1)) {
                if (Direction.WEST.opposite(map[start.row][start.column - 1]) != null) {
                    if (isSecond) {
                        return Direction.WEST;
                    } else isSecond = true;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    @Getter
    public class Tile {
        private Coordinate coordinate;
        private Direction direction;
        private char sign;

        public Tile(Coordinate coordinate, Direction direction, char sign) {
            this.coordinate = coordinate;
            this.direction = direction;
            this.sign = sign;
        }
    }

    @Getter
    public class Route {
        private List<Tile> route = new ArrayList<>();
        private Map<Coordinate, Character> routeMap;

        public Route(PipeMap pipeMap) {
            Coordinate coordinate = pipeMap.getStartPoint(START_SIGN);
            Direction directionS = pipeMap.getSDirection(coordinate);
            Direction direction = pipeMap.getStartDirection(coordinate);
            char sign = Direction.getSignByDirections(directionS, direction);

            this.route.add(new Tile(coordinate, direction, sign));

            do {
                coordinate = direction.move(coordinate);
                sign = pipeMap.getTile(coordinate);
                direction = direction.opposite(sign);
                this.route.add(new Tile(coordinate, direction, sign));
            } while (sign != START_SIGN);

            this.route.removeLast();

            this.routeMap = this.route.stream()
                    .collect(toMap(Tile::getCoordinate, Tile::getSign));
        }
    }


    public enum Direction {
        NORTH {
            @Override
            public Direction opposite(char sign) {
                return switch (sign) {
                    case '|' -> NORTH;
                    case '7' -> WEST;
                    case 'F' -> EAST;
                    default -> null;
                };
            }

            @Override
            public Coordinate move(Coordinate current) {
                return current.up();
            }
        }, EAST {
            @Override
            public Direction opposite(char sign) {
                return switch (sign) {
                    case '-' -> EAST;
                    case '7' -> SOUTH;
                    case 'J' -> NORTH;
                    default -> null;
                };
            }

            @Override
            public Coordinate move(Coordinate current) {
                return current.right();
            }
        }, SOUTH {
            @Override
            public Direction opposite(char sign) {
                return switch (sign) {
                    case '|' -> SOUTH;
                    case 'J' -> WEST;
                    case 'L' -> EAST;
                    default -> null;
                };
            }

            @Override
            public Coordinate move(Coordinate current) {
                return current.down();
            }
        }, WEST {
            @Override
            public Direction opposite(char sign) {
                return switch (sign) {
                    case '-' -> WEST;
                    case 'L' -> NORTH;
                    case 'F' -> SOUTH;
                    default -> null;
                };
            }

            @Override
            public Coordinate move(Coordinate current) {
                return current.left();
            }
        };

        public static char getSignByDirections(Direction dir1, Direction dir2) {
            if ((dir1 == Direction.NORTH && dir2 == Direction.SOUTH) || (dir2 == Direction.NORTH && dir1 == Direction.SOUTH)) {
                return '|';
            }
            if ((dir1 == Direction.EAST && dir2 == Direction.WEST) || (dir2 == Direction.EAST && dir1 == Direction.WEST)) {
                return '-';
            }
            if ((dir1 == Direction.NORTH && dir2 == Direction.EAST) || (dir2 == Direction.NORTH && dir1 == Direction.EAST)) {
                return 'L';
            }
            if ((dir1 == Direction.EAST && dir2 == Direction.SOUTH) || (dir2 == Direction.EAST && dir1 == Direction.SOUTH)) {
                return 'F';
            }
            if ((dir1 == Direction.WEST && dir2 == Direction.SOUTH) || (dir2 == Direction.WEST && dir1 == Direction.SOUTH)) {
                return '7';
            }
            if ((dir1 == Direction.NORTH && dir2 == Direction.WEST) || (dir2 == Direction.NORTH && dir1 == Direction.WEST)) {
                return 'J';
            }
            throw new IllegalArgumentException();
        }

        public abstract Coordinate move(Coordinate current);

        public abstract Direction opposite(char sign);


    }

    public static final class Coordinate {
        private int row;
        private int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public Coordinate up() {
            return new Coordinate(row - 1, column);
        }

        public Coordinate down() {
            return new Coordinate(row + 1, column);
        }

        public Coordinate right() {
            return new Coordinate(row, column + 1);
        }

        public Coordinate left() {
            return new Coordinate(row, column - 1);
        }

        public Coordinate upRight() {
            return new Coordinate(row - 1, column + 1);
        }

        public Coordinate downRight() {
            return new Coordinate(row + 1, column + 1);
        }

        public Coordinate downLeft() {
            return new Coordinate(row + 1, column - 1);
        }

        public Coordinate upLeft() {
            return new Coordinate(row - 1, column - 1);
        }

        public Coordinate upThis() {
            row--;
            return this;
        }

        public Coordinate downThis() {
            row++;
            return this;
        }

        public Coordinate rightThis() {
            column++;
            return this;
        }

        public Coordinate leftThis() {
            column--;
            return this;
        }

        public Coordinate upRightThis() {
            row--;
            column++;
            return this;
        }

        public Coordinate downRightThis() {
            row++;
            column++;
            return this;
        }

        public Coordinate downLeftThis() {
            row++;
            column--;
            return this;
        }

        public Coordinate upLeftThis() {
            row--;
            column--;
            return this;
        }


        public boolean isInBorderOf(char[][] map) {
            return Utils.isIndexExistInArray(map, row, column);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }


}

