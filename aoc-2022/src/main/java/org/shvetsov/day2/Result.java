package org.shvetsov.day2;

import lombok.Getter;

import static org.shvetsov.day2.Shape.*;

@Getter
public enum Result {
    LOSE(0) {
        @Override
        public int play(Shape opponent) {
            return switch (opponent) {
                case ROCK -> this.getPoint() + SCISSORS.getPoint();
                case PAPER -> this.getPoint() + ROCK.getPoint();
                case SCISSORS -> this.getPoint() + PAPER.getPoint();
            };
        }
    },
    DRAW(3) {
        @Override
        public int play(Shape opponent) {
            return this.getPoint() + opponent.getPoint();
        }
    },
    WIN(6) {
        @Override
        public int play(Shape opponent) {
            return switch (opponent) {
                case ROCK -> this.getPoint() + PAPER.getPoint();
                case PAPER -> this.getPoint() + SCISSORS.getPoint();
                case SCISSORS -> this.getPoint() + ROCK.getPoint();
            };
        }
    },
    ;

    private final int point;

    Result(int point) {
        this.point = point;
    }

    public abstract int play(Shape opponent);

    public static Result of(String input) {
        return switch (input) {
            case "X" -> LOSE;
            case "Y" -> DRAW;
            case "Z" -> WIN;
            default -> throw new IllegalArgumentException("Invalid Result: " + input);
        };
    }


}
