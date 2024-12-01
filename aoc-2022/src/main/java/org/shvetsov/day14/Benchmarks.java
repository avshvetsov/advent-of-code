package org.shvetsov.day14;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_14_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_14_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt   Score   Error  Units
//Benchmarks.day14PartOneAnton  avgt    3   2,643 ± 5,029  ms/op
//Benchmarks.day14PartTwoAnton  avgt    3  26,862 ± 7,015  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day14PartOneAnton(Blackhole bh) {
        bh.consume(new Day14().partOne(Utils.parseInputByNewLine(DAY_14_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day14PartTwoAnton(Blackhole bh) {
        bh.consume(new Day14().partTwo(Utils.parseInputByNewLine(DAY_14_PART_2_REAL_ANTON.getInput(PATH))));
    }
}