package org.shvetsov.playground.implementations;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.shvetsov.playground.helpers.Owner;
import org.shvetsov.playground.helpers.Owners;
import org.shvetsov.playground.tasks.SortArrayTask;

import java.util.List;

@Owner(Owners.DANIIL)
@State(Scope.Benchmark)
public class DaniilSortArrayTask implements SortArrayTask {
    @Override
    public List<Integer> run(List<Integer> inStructure) {
        return inStructure.stream().sorted().toList();
    }
}
