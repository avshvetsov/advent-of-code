package org.shvetsov.day13;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_13_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_13_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                         Mode  Cnt  Score   Error  Units
//Benchmarks.day13PartOneAntonUgly  avgt    3  1,234 ± 0,163  ms/op
//Benchmarks.day13PartOneDany       avgt    3  1,333 ± 0,499  ms/op
//Benchmarks.day13PartTwoAntonUgly  avgt    3  1,612 ± 1,250  ms/op
//Benchmarks.day13PartTwoDany       avgt    3  1,580 ± 0,407  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day13PartOneDany(Blackhole bh) {
        bh.consume(new Day13().partOne(Utils.parseInputByNewLine(DAY_13_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day13PartTwoDany(Blackhole bh) {
        bh.consume(new Day13().partTwo(Utils.parseInputByNewLine(DAY_13_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day13PartOneAntonUgly(Blackhole bh) {
        bh.consume(new Day13Ugly().partOne(Utils.parseInputByNewLine(DAY_13_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day13PartTwoAntonUgly(Blackhole bh) {
        bh.consume(new Day13Ugly().partTwo(Utils.parseInputByNewLine(DAY_13_PART_2_REAL_ANTON.getInput(PATH))));
    }
}