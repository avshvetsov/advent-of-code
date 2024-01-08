package org.shvetsov.utils;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Point {
    public int r;
    public int c;

    private Point(int row, int column) {
        this.r = row;
        this.c = column;
    }

    public static Point of(int row, int column) {
        return new Point(row, column);
    }

    public boolean isIndexExistInArray(char[][] array) {
        return Utils.isIndexExistInArray(array, r, c);
    }

    public int distance(Point target) {
        return Math.abs(target.r - this.r) + Math.abs(target.c - this.c);
    }

    public Point move(Direction direction) {
        return switch (direction) {
            case NORTH -> up();
            case EAST -> right();
            case SOUTH -> down();
            case WEST -> left();
        };
    }
    public Point moveThis(Direction direction) {
        return switch (direction) {
            case NORTH -> upThis();
            case EAST -> rightThis();
            case SOUTH -> downThis();
            case WEST -> leftThis();
        };
    }

    public Point up() {
        return new Point(r - 1, c);
    }

    public Point down() {
        return new Point(r + 1, c);
    }

    public Point right() {
        return new Point(r, c + 1);
    }

    public Point left() {
        return new Point(r, c - 1);
    }

    public Point upRight() {
        return new Point(r - 1, c + 1);
    }

    public Point downRight() {
        return new Point(r + 1, c + 1);
    }

    public Point downLeft() {
        return new Point(r + 1, c - 1);
    }

    public Point upLeft() {
        return new Point(r - 1, c - 1);
    }

    public Point upThis() {
        r--;
        return this;
    }

    public Point downThis() {
        r++;
        return this;
    }

    public Point rightThis() {
        c++;
        return this;
    }

    public Point leftThis() {
        c--;
        return this;
    }

    public Point upRightThis() {
        r--;
        c++;
        return this;
    }

    public Point downRightThis() {
        r++;
        c++;
        return this;
    }

    public Point downLeftThis() {
        r++;
        c--;
        return this;
    }

    public Point upLeftThis() {
        r--;
        c--;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return r == that.r && c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    @Override
    public String toString() {
        return "{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }
}
