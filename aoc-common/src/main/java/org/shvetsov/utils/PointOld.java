package org.shvetsov.utils;

import lombok.Getter;

import java.util.Objects;

@Getter
public class PointOld {
    public int r;
    public int c;

    private PointOld(int row, int column) {
        this.r = row;
        this.c = column;
    }

    public static PointOld of(int row, int column) {
        return new PointOld(row, column);
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

    public int distance(PointOld target) {
        return Math.abs(target.r - this.r) + Math.abs(target.c - this.c);
    }

    public PointOld move(Direction direction) {
        return switch (direction) {
            case NORTH -> up();
            case EAST -> right();
            case SOUTH -> down();
            case WEST -> left();
        };
    }

    public PointOld move(Direction direction, int length) {
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

    public void moveThis(int[] direction) {
        r += direction[0];
        c += direction[1];
    }

    public PointOld up() {
        return up(1);
    }
    public PointOld up(int length) {
        return new PointOld(r - length, c);
    }

    public PointOld down() {
        return down(1);
    }
    public PointOld down(int length) {
        return new PointOld(r + length, c);
    }

    public PointOld right() {
        return right(1);
    }
    public PointOld right(int length) {
        return new PointOld(r, c + length);
    }

    public PointOld left() {
        return left(1);
    }
    public PointOld left(int length) {
        return new PointOld(r, c - length);
    }

    public PointOld upRight() {
        return new PointOld(r - 1, c + 1);
    }

    public PointOld downRight() {
        return new PointOld(r + 1, c + 1);
    }

    public PointOld downLeft() {
        return new PointOld(r + 1, c - 1);
    }

    public PointOld upLeft() {
        return new PointOld(r - 1, c - 1);
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

    public PointOld upRightThis() {
        r--;
        c++;
        return this;
    }

    public PointOld downRightThis() {
        r++;
        c++;
        return this;
    }

    public PointOld downLeftThis() {
        r++;
        c--;
        return this;
    }

    public PointOld upLeftThis() {
        r--;
        c--;
        return this;
    }

    public PointOld subtract(PointOld other) {
        return PointOld.of(r - other.r, c - other.c);
    }

    public PointOld add(PointOld other) {
        return PointOld.of(r + other.r, c + other.c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOld that = (PointOld) o;
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
