package org.shvetsov.day17;

import lombok.Getter;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Grid;
import org.shvetsov.utils.PointOld;

import java.util.Objects;

@Getter
public class Node implements Comparable<Node> {
    private final Key key;
    private final Integer minHeatLossFromStart;
    private final Node prev;

    public Node(Key key, Integer minHeatLossFromStart, Node prev) {
        this.key = key;
        this.minHeatLossFromStart = minHeatLossFromStart;
        this.prev = prev;
    }

    public Node buildNewNode(Grid<CityBlock> grid, Direction direction) {
        Integer line = key.direction() != null && key.direction().equals(direction) ? key.line() + 1 : 1;
        PointOld point = key.point().move(direction);
        return new Node(new Key(point, direction, line), minHeatLossFromStart + grid.getValue(point).getHeatLoss(), this);
    }


    @Override
    public int compareTo(Node o) {
        if (!this.minHeatLossFromStart.equals(o.getMinHeatLossFromStart())) {
            return Integer.compare(this.minHeatLossFromStart, o.getMinHeatLossFromStart());
        } else {
            return Integer.compare(this.key.line(), o.getKey().line());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(key, node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
