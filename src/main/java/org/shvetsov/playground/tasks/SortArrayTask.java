package org.shvetsov.playground.tasks;

import java.util.List;

public interface SortArrayTask extends Task<List<Integer>, List<Integer>> {
    @Override
    default List<Integer> getTestInput() {
        return List.of(3, 2, 1);
    };
}
