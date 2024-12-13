package org.shvetsov.day13;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_13_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_13_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt  Score   Error  Units
//Benchmarks.day13PartOneAnton  avgt    3  0,175 ± 0,049  ms/op
//Benchmarks.day13PartTwoAnton  avgt    3  0,184 ± 0,050  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day13PartOneAnton(Blackhole bh) {
        bh.consume(new Day13().partOne(Utils.parseInputByNewLine(DAY_13_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day13PartTwoAnton(Blackhole bh) {
        bh.consume(new Day13().partTwo(Utils.parseInputByNewLine(DAY_13_PART_2_REAL_ANTON.getInput(PATH))));
    }
}