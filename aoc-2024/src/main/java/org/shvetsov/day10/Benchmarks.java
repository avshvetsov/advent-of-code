package org.shvetsov.day10;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_10_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_10_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt  Score   Error  Units
//Benchmarks.day10PartOneAnton  avgt    3  0,325 ± 0,201  ms/op
//Benchmarks.day10PartTwoAnton  avgt    3  0,303 ± 0,086  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day10PartOneAnton(Blackhole bh) {
        bh.consume(new Day10().partOne(Utils.parseInputByNewLine(DAY_10_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day10PartTwoAnton(Blackhole bh) {
        bh.consume(new Day10().partTwo(Utils.parseInputByNewLine(DAY_10_PART_2_REAL_ANTON.getInput(PATH))));
    }
}