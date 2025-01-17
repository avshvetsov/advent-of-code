package org.shvetsov.day23;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_23_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_23_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                                 Mode  Cnt    Score    Error  Units
//Benchmarks.day23PartOneAnton              avgt    3    5,940 ±  0,210  ms/op
//Benchmarks.day23PartOneBronKerboschAnton  avgt    3  175,005 ± 65,035  ms/op
//Benchmarks.day23PartTwoAnton              avgt    3  153,548 ± 18,533  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day23PartOneAnton(Blackhole bh) {
        bh.consume(new Day23().partOne(Utils.parseInputByNewLine(DAY_23_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day23PartOneBronKerboschAnton(Blackhole bh) {
        bh.consume(new Day23().partOneBronKerbosch(Utils.parseInputByNewLine(DAY_23_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day23PartTwoAnton(Blackhole bh) {
        bh.consume(new Day23().partTwo(Utils.parseInputByNewLine(DAY_23_PART_2_REAL_ANTON.getInput(PATH))));
    }
}