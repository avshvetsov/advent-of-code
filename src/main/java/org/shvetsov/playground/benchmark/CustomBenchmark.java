package org.shvetsov.playground.benchmark;

import org.openjdk.jmh.annotations.*;
import org.shvetsov.playground.implementations.DaniilSortArrayTask;
import org.shvetsov.playground.tasks.Task;

public class CustomBenchmark {


    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void run(DaniilSortArrayTask task) {
        task.run(task.getTestInput());
    }

}
