package org.shvetsov.day15;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/15">Day 15</a>
 */
public class Day15 {

    public long partOne(List<String> input) {
        Point robot = null;
        char[][] map = new char[input.getFirst().length()][input.getFirst().length()];
        for (int i = 0; i < input.getFirst().length(); i++) {
            for (int j = 0; j < input.getFirst().length(); j++) {
                map[i][j] = input.get(i).charAt(j);
                if (map[i][j] == '@') {
                    robot = new Point(i, j);
                }
            }
        }
        StringBuilder actionsSB = new StringBuilder();
        for (int i = input.getFirst().length() + 1; i < input.size(); i++) {
            actionsSB.append(input.get(i));
        }
        char[] actions = actionsSB.toString().toCharArray();
        for (char action : actions) {
            robot = moveRobot(map, action, robot);
        }
//        PrintUtils.printArray(map);
        long result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'O') {
                    result += i * 100L + j;
                }
            }
        }
        return result;
    }

    private Point moveRobot(char[][] map, char action, Point robot) {
        Direction dir = switch (action) {
            case '<' -> Direction.WEST;
            case '>' -> Direction.EAST;
            case '^' -> Direction.NORTH;
            case 'v' -> Direction.SOUTH;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        Point potentialRobotPos = robot.move(dir);
        Point next = robot.move(dir);
        while (map[next.r()][next.c()] == 'O') {
            next = next.move(dir);
        }
        if (map[next.r()][next.c()] == '#') {
            return robot;
        } else /*if (map[next.r()][next.c()] == '.')*/ {
            map[next.r()][next.c()] = 'O';
            map[robot.r()][robot.c()] = '.';
            map[potentialRobotPos.r()][potentialRobotPos.c()] = '@';
            return potentialRobotPos;
        }
    }


    public long partTwo(List<String> input) {
        Point robot = null;
        //build map
        char[][] map = new char[input.getFirst().length()][input.getFirst().length() * 2];
        for (int i = 0; i < input.getFirst().length(); i++) {
            for (int j = 0; j < input.getFirst().length(); j++) {
                switch (input.get(i).charAt(j)) {
                    case '@' -> {
                        robot = new Point(i, j * 2);
                        map[i][j * 2] = '@';
                        map[i][j * 2 + 1] = '.';
                    }
                    case 'O' -> {
                        map[i][j * 2] = '[';
                        map[i][j * 2 + 1] = ']';
                    }
                    case '#', '.' -> {
                        map[i][j * 2] = input.get(i).charAt(j);
                        map[i][j * 2 + 1] = input.get(i).charAt(j);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + input.get(i));
                }
            }
        }
        //build actions
        StringBuilder actionsSB = new StringBuilder();
        for (int i = input.getFirst().length() + 1; i < input.size(); i++) {
            actionsSB.append(input.get(i));
        }
        char[] actions = actionsSB.toString().toCharArray();
        //simulate moving
        for (char action : actions) {
            if (action == '>' || action == '<') {
                robot = moveRobotHorizontal(map, action, robot);
            } else if (action == '^' || action == 'v') {
                Direction dir = switch (action) {
                    case '^' -> Direction.NORTH;
                    case 'v' -> Direction.SOUTH;
                    default -> throw new IllegalStateException("Unexpected value: " + action);
                };
                if (moveRobotVertical(map, dir, Set.of(new PointCharacter(robot, '@')))) {
                    map[robot.r()][robot.c()] = '.';
                    robot = robot.move(dir);
                    if (map[robot.r()][robot.c() + 1] == ']') {
                        map[robot.r()][robot.c() + 1] = '.';
                    } else if (map[robot.r()][robot.c() - 1] == '[') {
                        map[robot.r()][robot.c() - 1] = '.';
                    }
                }
            }
        }
//        PrintUtils.printArray(map);
        //count result
        long result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '[') {
                    result += i * 100L + j;
                }
            }
        }
        return result;
    }

    private Point moveRobotHorizontal(char[][] map, char action, Point robot) {
        Direction dir = switch (action) {
            case '<' -> Direction.WEST;
            case '>' -> Direction.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        Point next = robot.move(dir);
        while (map[next.r()][next.c()] == '[' || map[next.r()][next.c()] == ']') {
            next = next.move(dir);
        }
        if (map[next.r()][next.c()] == '#') {
            return robot;
        } else /*if (map[next.r()][next.c()] == '.')*/ {
            Direction opposite = dir.opposite();
            Point current = next;
            next = next.move(opposite);
            while (!next.equals(robot)) {
                map[current.r()][current.c()] = map[next.r()][next.c()];
                current = next;
                next = next.move(opposite);
            }
            map[current.r()][current.c()] = '@';
            map[next.r()][next.c()] = '.';
            return current;
        }
    }

    private boolean moveRobotVertical(char[][] map, Direction dir, Set<PointCharacter> current) {
        Set<Character> characters = current.stream().map(PointCharacter::character).collect(Collectors.toSet());
        if (characters.contains('#')) {
            return false;
        } else if (characters.size() == 1 && characters.contains('.')) {
            return true;
        } else {
            Set<PointCharacter> next = new HashSet<>();
            current.stream()
                    .filter(pointCharacter -> pointCharacter.character() == '[' || pointCharacter.character() == ']' || pointCharacter.character() == '@')
                    .forEach(pointCharacter -> next.addAll(getNextPointCharacters(pointCharacter, map, dir)));
            if (moveRobotVertical(map, dir, next)) {
                for (PointCharacter pointCharacter : current) {
                    if (pointCharacter.character() != '.') {
                        Point pointCurrent = pointCharacter.point();
                        Point pointNext = pointCurrent.move(dir);
                        map[pointNext.r()][pointNext.c()] = map[pointCurrent.r()][pointNext.c()];
                        map[pointCurrent.r()][pointNext.c()] = '.';
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    private Set<PointCharacter> getNextPointCharacters(PointCharacter current, char[][] map, Direction dir) {
        Set<PointCharacter> result = new HashSet<>();
        Point nextPoint = current.point().move(dir);
        switch (map[nextPoint.r()][nextPoint.c()]) {
            case '[' -> {
                result.add(new PointCharacter(nextPoint, map[nextPoint.r()][nextPoint.c()]));
                Point nextSecond = nextPoint.move(Direction.EAST);
                result.add(new PointCharacter(nextSecond, map[nextSecond.r()][nextSecond.c()]));
            }
            case ']' -> {
                result.add(new PointCharacter(nextPoint, map[nextPoint.r()][nextPoint.c()]));
                Point nextSecond = nextPoint.move(Direction.WEST);
                result.add(new PointCharacter(nextSecond, map[nextSecond.r()][nextSecond.c()]));
            }
            default -> result.add(new PointCharacter(nextPoint, map[nextPoint.r()][nextPoint.c()]));
        }
        return result;
    }

    record PointCharacter(Point point, Character character) {
    }

}