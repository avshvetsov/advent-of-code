package org.shvetsov.day17;

import org.shvetsov.utils.Direction;
import org.shvetsov.utils.PointOld;

public record Key(PointOld point, Direction direction, Integer line) {
}
