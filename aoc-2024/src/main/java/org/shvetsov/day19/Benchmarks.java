package org.shvetsov.day19;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_19_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_19_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt    Score    Error  Units
//Benchmarks.day19PartOneAnton  avgt    3   71,104 ±  9,549  ms/op
//Benchmarks.day19PartTwoAnton  avgt    3  107,961 ± 22,789  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day19PartOneAnton(Blackhole bh) {
        bh.consume(new Day19().partOne(Utils.parseInputByNewLine(DAY_19_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day19PartTwoAnton(Blackhole bh) {
        bh.consume(new Day19().partTwo(Utils.parseInputByNewLine(DAY_19_PART_2_REAL_ANTON.getInput(PATH))));
    }
}