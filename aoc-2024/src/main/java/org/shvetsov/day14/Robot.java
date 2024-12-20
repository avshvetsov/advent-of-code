package org.shvetsov.day14;

import lombok.Getter;
import org.shvetsov.utils.Point;

@Getter
public class Robot {

    private Point position;
    private final Point velocity;

    public Robot(Point position, Point velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void move(int[] boundary) {
        this.position = new Point((position.r() + velocity.r() + boundary[1]) % boundary[1], (position.c() + velocity.c() + boundary[0]) % boundary[0]);
    }

    public int getQuadrant(int[] boundary) {
        int mx = boundary[0] / 2;
        int my = boundary[1] / 2;
        if (position.c() < mx && position.r() < my) {
            return 0;
        } else if (position.c() > mx && position.r() < my) {
            return 1;
        } else if (position.c() < mx && position.r() > my) {
            return 2;
        } else if (position.c() > mx && position.r() > my) {
            return 3;
        } else {
            return -1;
        }
    }
}
