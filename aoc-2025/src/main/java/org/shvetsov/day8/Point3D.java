package org.shvetsov.day8;

public record Point3D(int x, int y, int z) {

    public long squareDistance(Point3D other) {
        return (long) Math.abs(x - other.x) * Math.abs(x - other.x) + (long) Math.abs(y - other.y) * Math.abs(y - other.y) + (long) Math.abs(z - other.z) * Math.abs(z - other.z);
    }

    public double distance(Point3D other) {
        return Math.sqrt(squareDistance(other));
    }
}
