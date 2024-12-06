package org.shvetsov.utils;

import org.apache.commons.math3.complex.Complex;

public enum Direction {
    NORTH, WEST, SOUTH, EAST;

    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case WEST -> EAST;
            case SOUTH -> NORTH;
            case EAST -> WEST;
        };
    }

    public Complex toComplex() {
        return switch (this) {
            case NORTH -> new Complex(0, 1);
            case WEST -> new Complex(-1, 0);
            case SOUTH -> new Complex(0, -1);
            case EAST -> new Complex(1, 0);
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case WEST -> NORTH;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
        };
    }
}
