package org.shvetsov.day7;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_7_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_7_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                                            Mode  Cnt     Score     Error  Units
//Benchmarks.day7PartOneAnton                          avgt    3    20,939 ±   1,732  ms/op
//Benchmarks.day7PartOneRecursionAnton                 avgt    3    22,274 ±   0,942  ms/op
//Benchmarks.day7PartOneRecursionWithAccumulatorAnton  avgt    3    21,643 ±  12,307  ms/op
//Benchmarks.day7PartTwoAnton                          avgt    3  1052,287 ± 136,529  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day7PartOneAnton(Blackhole bh) {
        bh.consume(new Day7().partOne(Utils.parseInputByNewLine(DAY_7_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day7PartOneRecursionWithAccumulatorAnton(Blackhole bh) {
        bh.consume(new Day7().partOneRecursionWithAccumulator(Utils.parseInputByNewLine(DAY_7_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark

    public void day7PartOneRecursionAnton(Blackhole bh) {
        bh.consume(new Day7().partOneRecursion(Utils.parseInputByNewLine(DAY_7_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day7PartTwoAnton(Blackhole bh) {
        bh.consume(new Day7().partTwo(Utils.parseInputByNewLine(DAY_7_PART_2_REAL_ANTON.getInput(PATH))));
    }
}