package org.shvetsov.day18;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2023/day/18">Day 18</a>
 */
public class Day18 {

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> inputList) {
        DigPlan plan = new DigPlan(inputList);
        plan.sort();
        return plan.getSize();
    }

    public long partTwoAnton(List<String> inputList) {
        return 0;
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
                    } else this.edges.add(new DigCube(Point.of(0, 0), d, c));
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

            private Point point;
            private Direction direction;
            private Character sign;
            private String color;

            public DigCube(Point point, Direction direction, String color) {
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