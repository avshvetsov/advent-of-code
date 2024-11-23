package org.shvetsov.day2;

import lombok.Getter;

import static org.shvetsov.day2.Result.*;

@Getter
public enum Shape {
    ROCK(1) {
        @Override
        public int play(Shape opponent) {
            return switch (opponent) {
                case ROCK -> this.getPoint() + DRAW.getPoint();
                case PAPER -> this.getPoint() + LOSE.getPoint();
                case SCISSORS -> this.getPoint() + WIN.getPoint();
            };
        }
    },
    PAPER(2) {
        @Override
        public int play(Shape opponent) {
            return switch (opponent) {
                case ROCK -> this.getPoint() + WIN.getPoint();
                case PAPER -> this.getPoint() + DRAW.getPoint();
                case SCISSORS -> this.getPoint() + LOSE.getPoint();
            };
        }
    },
    SCISSORS(3) {
        @Override
        public int play(Shape opponent) {
            return switch (opponent) {
                case ROCK -> this.getPoint() + LOSE.getPoint();
                case PAPER -> this.getPoint() + WIN.getPoint();
                case SCISSORS -> this.getPoint() + DRAW.getPoint();
            };
        }
    },
    ;

    private final int point;

    Shape(int point) {
        this.point = point;
    }

    public abstract int play(Shape opponent);

    public static Shape of(String input) {
        return switch (input) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> throw new IllegalArgumentException("Invalid RPS: " + input);
        };
    }
}
