package org.shvetsov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class Benchmarks {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1, time = 5)
    @Measurement(iterations = 3, time = 5)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void task1_1(Blackhole bh) {
        bh.consume(new Day1().partOne(Utils.parseInputByNewLine("src/main/resources/input 1-1, 1-2.txt")));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1, time = 5)
    @Measurement(iterations = 3, time = 5)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void task1_2(Blackhole bh) {
        bh.consume(new Day1().partTwo(Utils.parseInputByNewLine("src/main/resources/input 1-1, 1-2.txt")));
    }
}
