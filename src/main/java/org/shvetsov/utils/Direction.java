package org.shvetsov.utils;

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
}
