package org.shvetsov.day17;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Point;

public record Key(Point point, Direction direction, Integer line) {
}
