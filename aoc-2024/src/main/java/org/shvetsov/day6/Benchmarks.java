package org.shvetsov.day6;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_6_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_6_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                    Mode  Cnt    Score    Error  Units
//Benchmarks.day6PartOneAnton  avgt    3    0,288 ±  0,088  ms/op
//Benchmarks.day6PartTwoAnton  avgt    3  864,410 ± 85,651  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day6PartOneAnton(Blackhole bh) {
        bh.consume(new Day6().partOne(Utils.parseInputByNewLine(DAY_6_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day6PartTwoAnton(Blackhole bh) {
        bh.consume(new Day6().partTwo(Utils.parseInputByNewLine(DAY_6_PART_2_REAL_ANTON.getInput(PATH))));
    }
}