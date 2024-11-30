package org.shvetsov.day18;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.PointOld;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2023/day/18">Day 18</a>
 */
public class Day18 {

    public static void main(String[] args) {

    }

    public int partOneAntonInefficient(List<String> inputList) {
        DigPlan plan = new DigPlan(inputList);
        plan.sort();
        return plan.getSize();
    }

    //In this task we need 2 forulas:
    //- https://en.wikipedia.org/wiki/Shoelace_formula - for rectangular polygon it simplifies to src/main/resources/day18/Shoelace formula.png
    //- https://en.wikipedia.org/wiki/Pick%27s_theorem
    public int partOneAnton(List<String> inputList) {
        int area = 0;
        int border = 0;
        PointOld point = PointOld.of(0, 1);
        for (int i = 0; i < inputList.size(); i++) {
            String[] split = inputList.get(i).split(" ");
            Direction direction = getDirection(split[0].charAt(0));
            int length = Integer.parseInt(split[1]);
            point = point.move(direction, length);
            int sign = i % 2 == 0 ? 1 : -1;
//            Shoelace formula
            area += sign * point.r * point.c;
            border += length;
        }
//        Pick's theorem
        return (Math.abs(area) - border/2 + 1) + border;
    }

    public long partTwoAnton(List<String> inputList) {
        long area = 0L;
        long border = 0L;
        PointOld point = PointOld.of(0, 0);
        List<PointOld> points = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            String hexadecimal = StringUtils.substringBetween(inputList.get(i), "(#", ")");
            Direction direction = getDirection(hexadecimal.charAt(5));
            int length = Integer.parseInt(hexadecimal.substring(0, 5), 16);
            point = point.move(direction, length);
            points.add(point);
            long sign = i % 2 == 0 ? 1L : -1L;
//            Shoelace formula
            area += sign * point.r * point.c;
            border += length;
        }
//        Pick's theorem
        return (Math.abs(area) - border/2 + 1) + border;
    }

    private Direction getDirection(Character character) {
        return switch (character) {
            case '0', 'R' -> Direction.EAST;
            case '1', 'D' -> Direction.SOUTH;
            case '2', 'L' -> Direction.WEST;
            case '3', 'U' -> Direction.NORTH;
            default -> throw new IllegalArgumentException("Illegal direction character");
        };
    }

    @Getter
    private static class DigPlan {

        private final List<DigCube> edges = new ArrayList<>();

        public DigPlan(List<String> inputList) {
            for (String s : inputList) {
                String[] split = s.split(" ");
                Direction d = switch (split[0]) {
                    case "R" -> Direction.EAST;
                    case "D" -> Direction.SOUTH;
                    case "L" -> Direction.WEST;
                    case "U" -> Direction.NORTH;
                    default -> throw new IllegalStateException("Unexpected value: " + split[0]);
                };
                String c = StringUtils.substringBetween(split[2], "(", ")");
                for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                    if (!this.edges.isEmpty()) {
                        DigCube last = this.edges.getLast();
                        last.setSign(d);
                        this.edges.add(new DigCube(last.getPoint().move(d), d, c));
                    } else this.edges.add(new DigCube(PointOld.of(0, 0), d, c));
                }
            }
            this.edges.getLast().setSign(this.edges.getFirst().getDirection());
        }

        public void sort() {
            this.edges.sort((o1, o2) -> o1.getPoint().r != o2.getPoint().r ? Integer.compare(o1.getPoint().r, o2.getPoint().r) : Integer.compare(o1.getPoint().c, o2.getPoint().c));
        }

        public int getSize() {
            int size = 0;
            Integer lastRow = null;
            Integer lastColumn = null;
            boolean isInside = false;
            for (DigCube cube : edges) {
                if (lastRow == null && lastColumn == null) {
                    lastRow = cube.getPoint().getR();
                    lastColumn = cube.getPoint().getC();
                    size++;
                    continue;
                }
                Integer currentRow = cube.getPoint().getR();
                Integer currentColumn = cube.getPoint().getC();
                if (!currentRow.equals(lastRow)) {
                    isInside = false;
                }

                if (currentColumn - lastColumn > 1) {
                    if (isInside) {
                        size += (currentColumn - lastColumn);
                    } else {
                        size++;
                    }
                } else {
                    size++;
                }
                lastRow = currentRow;
                lastColumn = currentColumn;
                if (cube.getSign() == '|' || cube.getSign() == 'F' || cube.getSign() == '7') {
                    isInside = !isInside;
                }
            }
            return size;
        }

        @Data
        private static class DigCube {

            private PointOld point;
            private Direction direction;
            private Character sign;
            private String color;

            public DigCube(PointOld point, Direction direction, String color) {
                this.point = point;
                this.direction = direction;
                this.color = color;
            }

            public void setSign(Direction nextDirection) {
                if (this.direction.equals(nextDirection)) {
                    if (this.direction.equals(Direction.NORTH) || this.direction.equals(Direction.SOUTH)) {
                        this.sign = '|';
                    } else {
                        this.sign = '-';
                    }
                    return;
                }

                if ((direction == Direction.EAST && nextDirection == Direction.SOUTH) || (direction == Direction.NORTH && nextDirection == Direction.WEST)) {
                    this.sign = '7';
                }
                if ((direction == Direction.SOUTH && nextDirection == Direction.WEST) || (direction == Direction.EAST && nextDirection == Direction.NORTH)) {
                    this.sign = 'J';
                }
                if ((direction == Direction.WEST && nextDirection == Direction.NORTH) || (direction == Direction.SOUTH && nextDirection == Direction.EAST)) {
                    this.sign = 'L';
                }
                if ((direction == Direction.NORTH && nextDirection == Direction.EAST) || (direction == Direction.WEST && nextDirection == Direction.SOUTH)) {
                    this.sign = 'F';
                }
            }
        }
    }
}