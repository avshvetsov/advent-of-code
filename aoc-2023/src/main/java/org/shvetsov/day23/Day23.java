package org.shvetsov.day23;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.PointOld;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * <a href="https://adventofcode.com/2023/day/23">Day 23</a>
 */
public class Day23 {

    private static final PointOld START = PointOld.of(0, 1);

    public long partOneAnton(List<String> inputList) {
        char[][] map = buildMap(inputList);
        PointOld END = PointOld.of(map.length - 1, map[0].length - 2);
        return dfsBacktracking(map, START, END);
    }

    private static char[][] buildMap(List<String> inputList) {
        char[][] map = new char[inputList.size()][inputList.getFirst().length()];
        for (int i = 0; i < inputList.size(); i++) {
            map[i] = inputList.get(i).toCharArray();
        }
        return map;
    }

    private int dfsBacktracking(char[][] map, PointOld current, PointOld end) {
        if (current.equals(end)) {
            return 0;
        }
        int longestPathSize = -1;
        char currentSymbol = map[current.r][current.c];
        map[current.r][current.c] = '#';
        for (Direction dir : Direction.values()) {
            PointOld next = current.move(dir);
            if (next.isIndexExistInArray(map) && isItPossibleStep(map, dir, next)) {
                int nextPathLength = dfsBacktracking(map, next, end);
                if (nextPathLength >= 0) {
                    longestPathSize = Math.max(longestPathSize, nextPathLength + 1);
                }
            }

        }
        map[current.r][current.c] = currentSymbol;
        return longestPathSize;
    }

    private boolean isItPossibleStep(char[][] map, Direction dir, PointOld next) {
        return switch (map[next.r][next.c]) {
            case '#' -> false;
            case '.' -> true;
            case 'v' -> dir == Direction.SOUTH;
            case '>' -> dir == Direction.EAST;
            case '<' -> dir == Direction.WEST;
            default -> false;
        };
    }

    //run with -Xss4m
    public long partTwoAnton(List<String> inputList) {
        char[][] map = buildMap(inputList);
        PointOld END = PointOld.of(map.length - 1, map[0].length - 2);
        return dfsBacktracking2(map, START, END);
    }

    private int dfsBacktracking2(char[][] map, PointOld current, PointOld end) {
        if (current.equals(end)) {
            return 0;
        }
        int longestPathSize = -1;
        char currentSymbol = map[current.r][current.c];
        map[current.r][current.c] = '#';
        for (Direction dir : Direction.values()) {
            PointOld next = current.move(dir);
            if (next.isIndexExistInArray(map) && map[next.r][next.c] != '#') {
                int nextPathLength = dfsBacktracking2(map, next, end);
                if (nextPathLength >= 0) {
                    longestPathSize = Math.max(longestPathSize, nextPathLength + 1);
                }
            }

        }
        map[current.r][current.c] = currentSymbol;
        return longestPathSize;
    }


    //This way is not faster, cause it required to create map copy for each fork
    public long partTwoAntonParallel(List<String> inputList) {
        char[][] map = buildMap(inputList);
        PointOld END = PointOld.of(map.length - 1, map[0].length - 2);

        ForkJoinPool pool = new ForkJoinPool();
        ParallelComputation task = new ParallelComputation(map, START, END);
        Integer result = pool.invoke(task);
        return result - 1;
    }


}