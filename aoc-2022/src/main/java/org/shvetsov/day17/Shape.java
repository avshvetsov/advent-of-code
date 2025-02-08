package org.shvetsov.day17;

import org.shvetsov.utils.Point;

import java.util.Set;
import java.util.stream.Collectors;

public enum Shape {

    //    |..@@@@.|
    HORIZONTAL(Set.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3))),
    //    |...@...|
    //    |..@@@..|
    //    |...@...|
    CROSS(Set.of(new Point(-1, 0), new Point(0, 1), new Point(-1, 1), new Point(-2, 1), new Point(-1, 2))),
    //    |....@..|
    //    |....@..|
    //    |..@@@..|
    L_SHAPE(Set.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(-1, 2), new Point(-2, 2))),
    //    |..@....|
    //    |..@....|
    //    |..@....|
    //    |..@....|
    VERTICAL(Set.of(new Point(0, 0), new Point(-1, 0), new Point(-2, 0), new Point(-3, 0))),
    //    |..@@...|
    //    |..@@...|
    CUBE(Set.of(new Point(0, 0), new Point(0, 1), new Point(-1, 0), new Point(-1, 1))),
    ;

    private final Set<Point> points;

    Shape(Set<Point> points) {
        this.points = points;
    }

    public Set<Point> getShapePoints(Point base) {
        return points.stream()
                .map(point -> point.add(base))
                .collect(Collectors.toSet());
    }
}
