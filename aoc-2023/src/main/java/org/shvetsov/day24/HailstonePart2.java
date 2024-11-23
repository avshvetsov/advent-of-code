package org.shvetsov.day24;

import lombok.Getter;

@Getter
public class HailstonePart2 {

    public long x;
    public long y;
    public long z;
    public int vx;
    public int vy;
    public int vz;


    public HailstonePart2(String input) {
        String[] pointVelocity = input.split("@");
        String[] point = pointVelocity[0].split(",");
        String[] velocity = pointVelocity[1].split(",");
        x = Long.parseLong(point[0].trim());
        y = Long.parseLong(point[1].trim());
        z = Long.parseLong(point[2].trim());
        vx = Integer.parseInt(velocity[0].trim());
        vy = Integer.parseInt(velocity[1].trim());
        vz = Integer.parseInt(velocity[2].trim());
    }
}
