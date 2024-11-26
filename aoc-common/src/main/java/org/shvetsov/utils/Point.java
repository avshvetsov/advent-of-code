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

    public boolean isIndexExistInArray(int[][] array) {
        return Utils.isIndexExistInArray(array, r, c);
    }

    public <T> boolean isIndexExistInArray(T[][] array) {
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

    public Point move(Direction direction, int length) {
        return switch (direction) {
            case NORTH -> up(length);
            case EAST -> right(length);
            case SOUTH -> down(length);
            case WEST -> left(length);
        };
    }

    public void moveThis(Direction direction) {
        switch (direction) {
            case NORTH -> upThis();
            case EAST -> rightThis();
            case SOUTH -> downThis();
            case WEST -> leftThis();
        }
    }

    public Point up() {
        return up(1);
    }
    public Point up(int length) {
        return new Point(r - length, c);
    }

    public Point down() {
        return down(1);
    }
    public Point down(int length) {
        return new Point(r + length, c);
    }

    public Point right() {
        return right(1);
    }
    public Point right(int length) {
        return new Point(r, c + length);
    }

    public Point left() {
        return left(1);
    }
    public Point left(int length) {
        return new Point(r, c - length);
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

    public void upThis() {
        r--;
    }

    public void downThis() {
        r++;
    }

    public void rightThis() {
        c++;
    }

    public void leftThis() {
        c--;
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
