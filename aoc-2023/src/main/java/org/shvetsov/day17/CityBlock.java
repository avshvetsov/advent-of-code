package org.shvetsov.day17;

import lombok.Getter;
import lombok.Setter;
import org.shvetsov.utils.Direction;

import java.util.List;

@Getter
@Setter
public class CityBlock {

    private final Integer heatLoss;
    private Integer minHeatLossFromStart;
    private List<Direction> route;

    public CityBlock(Integer heatLoss) {
        this.heatLoss = heatLoss;
    }
}
