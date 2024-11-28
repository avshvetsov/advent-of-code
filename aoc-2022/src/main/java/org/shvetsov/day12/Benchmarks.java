package org.shvetsov.day12;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_12_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_12_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt    Score     Error  Units
//Benchmarks.day12PartOneAnton  avgt    3    2,801 ±   2,715  ms/op
//Benchmarks.day12PartTwoAnton  avgt    3  432,738 ± 657,313  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day12PartOneAnton(Blackhole bh) {
        bh.consume(new Day12().partOne(Utils.parseInputByNewLine(DAY_12_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day12PartTwoAnton(Blackhole bh) {
        bh.consume(new Day12().partTwo(Utils.parseInputByNewLine(DAY_12_PART_2_REAL_ANTON.getInput(PATH))));
    }
}