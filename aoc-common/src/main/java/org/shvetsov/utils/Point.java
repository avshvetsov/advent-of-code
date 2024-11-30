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
            case NORTH -> withR(r() - 1);
            case EAST -> withC(c() + 1);
            case SOUTH -> withR(r() + 1);
            case WEST -> withC(c() - 1);
        };
    }


    public Point subtract(Point other) {
        return new Point(r() - other.r(), c() - other.c());
    }

    public Point add(Point other) {
        return new Point(r() + other.r(), c() + other.c());
    }


}
