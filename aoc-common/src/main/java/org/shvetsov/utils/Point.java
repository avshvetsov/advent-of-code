package org.shvetsov.utils;

public record Point(int r, int c) {

    public Point withR(int r) {
        return new Point(r, c());
    }

    public Point withC(int c) {
        return new Point(r(), c);
    }

    public boolean isIndexExistInArray(char[][] array) {
        return Utils.isIndexExistInArray(array, r(), c());
    }

    public boolean isIndexExistInArray(int[][] array) {
        return Utils.isIndexExistInArray(array, r(), c());
    }

    public <T> boolean isIndexExistInArray(T[][] array) {
        return Utils.isIndexExistInArray(array, r(), c());
    }

    public int distance(Point target) {
        return Math.abs(target.r() - this.r()) + Math.abs(target.c() - this.c());
    }

    public Point move(Direction direction) {
        return switch (direction) {
            case NORTH -> up();
            case EAST -> right();
            case SOUTH -> down();
            case WEST -> left();
        };
    }

    public Point up() {
        return up(1);
    }
    public Point up(int steps) {
        return this.withR(r - steps);
    }

    public Point down() {
        return down(1);
    }
    public Point down(int steps) {
        return this.withR(r + steps);
    }

    public Point right() {
        return right(1);
    }
    public Point right(int steps) {
        return this.withC(c + steps);
    }

    public Point left() {
        return left(1);
    }
    public Point left(int steps) {
        return this.withC(c - steps);
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



    public Point subtract(Point other) {
        return new Point(r() - other.r(), c() - other.c());
    }

    public Point add(Point other) {
        return new Point(r() + other.r(), c() + other.c());
    }


}
