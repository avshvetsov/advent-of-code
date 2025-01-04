package org.shvetsov.day16;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.*;
import java.util.stream.Stream;

/**
 * <a href="https://adventofcode.com/2024/day/16">Day 16</a>
 */
public class Day16 {

    public long partOne(List<String> input) {
        Point startP = null, endP = null;
        char[][] map = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map[i][j] = input.get(i).charAt(j);
                if (map[i][j] == 'S') {
                    startP = new Point(i, j);
                } else if (map[i][j] == 'E') {
                    endP = new Point(i, j);
                }
            }
        }
        Vertex start = new Vertex(startP, Direction.EAST, 0);
        Set<Vertex> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparing(Vertex::cost));
        pq.add(start);
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            visited.add(current);
            if (current.point.equals(endP)) {
                return current.cost;
            }
            Vertex ahead = new Vertex(current.point.move(current.direction), current.direction, current.cost + 1);
            if (map[ahead.point.r()][ahead.point.c()] != '#' && !visited.contains(ahead)) {
                pq.add(ahead);
            }
            Vertex left = new Vertex(current.point.move(current.direction.turnLeft()), current.direction.turnLeft(), current.cost + 1001);
            if (map[left.point.r()][left.point.c()] != '#' && !visited.contains(left)) {
                pq.add(left);
            }
            Vertex right = new Vertex(current.point.move(current.direction.turnRight()), current.direction.turnRight(), current.cost + 1001);
            if (map[right.point.r()][right.point.c()] != '#' && !visited.contains(right)) {
                pq.add(right);
            }
        }
        throw new IllegalStateException("Path not found");
    }

    record Vertex(Point point, Direction direction, Integer cost) {

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Vertex vertex)) return false;

            return point.equals(vertex.point) && direction == vertex.direction;
        }

        @Override
        public int hashCode() {
            int result = point.hashCode();
            result = 31 * result + direction.hashCode();
            return result;
        }
    }


    public long partTwo(List<String> input) {
        Point startP = null, endP = null;
        char[][] map = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map[i][j] = input.get(i).charAt(j);
                if (map[i][j] == 'S') {
                    startP = new Point(i, j);
                } else if (map[i][j] == 'E') {
                    endP = new Point(i, j);
                }
            }
        }
        VertexWithPath start = new VertexWithPath(startP, Direction.EAST, 0, new ArrayList<>(List.of(startP)));
        Set<VertexWithPath> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<VertexWithPath> pq = new PriorityQueue<>(Comparator.comparing(VertexWithPath::cost));
        pq.add(start);
        while (!pq.isEmpty()) {
            VertexWithPath current = pq.poll();
            current.path.add(current.point);
            visited.add(current);
            if (current.point.equals(endP)) {
                Point finalEndP = endP;
                return Stream.concat(pq.stream()
                                        .filter(other -> other.cost.equals(current.cost))
                                        .filter(other -> other.point.equals(finalEndP))
                                        .flatMap(vertex -> vertex.path.stream())
                                , current.path.stream())
                        .distinct()
                        .count();
            }
            VertexWithPath ahead = new VertexWithPath(current.point.move(current.direction), current.direction, current.cost + 1, new ArrayList<>(current.path));
            if (map[ahead.point.r()][ahead.point.c()] != '#' && !visited.contains(ahead)) {
                pq.add(ahead);
            }
            VertexWithPath left = new VertexWithPath(current.point.move(current.direction.turnLeft()), current.direction.turnLeft(), current.cost + 1001, new ArrayList<>(current.path));
            if (map[left.point.r()][left.point.c()] != '#' && !visited.contains(left)) {
                pq.add(left);
            }
            VertexWithPath right = new VertexWithPath(current.point.move(current.direction.turnRight()), current.direction.turnRight(), current.cost + 1001, new ArrayList<>(current.path));
            if (map[right.point.r()][right.point.c()] != '#' && !visited.contains(right)) {
                pq.add(right);
            }
        }
        throw new IllegalStateException("Path not found");
    }

    record VertexWithPath(Point point, Direction direction, Integer cost, List<Point> path) {

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof VertexWithPath vertex)) return false;

            return point.equals(vertex.point) && direction == vertex.direction;
        }

        @Override
        public int hashCode() {
            int result = point.hashCode();
            result = 31 * result + direction.hashCode();
            return result;
        }
    }

}