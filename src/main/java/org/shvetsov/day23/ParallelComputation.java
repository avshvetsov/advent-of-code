package org.shvetsov.day23;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelComputation extends RecursiveTask<Integer> {

    private final char[][] map;
    private final Point start;
    private final Point end;

    public ParallelComputation(char[][] map, Point start, Point end) {
//        this.map = copyMap(map);
        this.map = map;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int currentPathSize = 0;
        List<Point> nextPoints = new ArrayList<>();
        nextPoints.add(start);
        do {
            currentPathSize++;
            Point current = nextPoints.getFirst();
            map[current.r][current.c] = '#';
            if (current.equals(end)) {
                return currentPathSize;
            }
            nextPoints = findNext(current);
        } while (nextPoints.size() == 1);

        if (nextPoints.isEmpty()) {
            return -1;
        } else {
            OptionalInt max = ForkJoinTask.invokeAll(split(nextPoints))
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .max();
            if (max.isPresent()) {
                return max.getAsInt() + currentPathSize;
            } else {
                return -1;
            }
        }
    }

    private List<Point> findNext(Point current) {
        List<Point> nextPoints = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            Point potentialNext = current.move(dir);
            if (potentialNext.isIndexExistInArray(map) && map[potentialNext.r][potentialNext.c] != '#') {
                nextPoints.add(potentialNext);
            }
        }
        return nextPoints;
    }

    private List<ParallelComputation> split(List<Point> nexts) {
        List<ParallelComputation> dividedTasks = new ArrayList<>();
        for (int i = 0; i < nexts.size(); i++) {
            Point next = nexts.get(i);
            if (i == 0) {
                dividedTasks.add(new ParallelComputation(map, next, end));
            } else {
                dividedTasks.add(new ParallelComputation(copyMap(map), next, end));
            }
        }
        return dividedTasks;
    }

    public static char[][] copyMap(char[][] map) {
        char[][] newMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}
