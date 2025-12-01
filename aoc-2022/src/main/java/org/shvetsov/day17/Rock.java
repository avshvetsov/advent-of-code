package org.shvetsov.day17;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

import java.util.HashSet;
import java.util.Set;

public class Rock {

    static final int SPAWN_HEIGHT = 5;
    static final int SPAWN_COLUMN = 2;

    private Set<Point> points;

    public Rock(int height, Shape shape) {
        Point base = new Point(height - SPAWN_HEIGHT, SPAWN_COLUMN);
        this.points = shape.getShapePoints(base);
    }

    public boolean moveIfCan(char[][] cave, Direction direction) {
        Set<Point> newPoints = new HashSet<>();
        for (Point p : this.points) {
            Point n = p.move(direction);
            if (n.c() < 0 || n.c() >= cave[0].length || cave[n.r()][n.c()] == '#') {
                return false;
            }
            newPoints.add(n);
        }
        this.points = newPoints;
        return true;
    }

    public Set<Point> getShape() {
        return this.points;
    }
}
