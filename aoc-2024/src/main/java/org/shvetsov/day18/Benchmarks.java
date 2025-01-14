package org.shvetsov.day18;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_18_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_18_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt    Score    Error  Units
//Benchmarks.day18PartOneAnton  avgt    3    0,802 ±  0,061  ms/op
//Benchmarks.day18PartTwoAnton  avgt    3  623,098 ± 79,302  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day18PartOneAnton(Blackhole bh) {
        bh.consume(new Day18().partOne(Utils.parseInputByNewLine(DAY_18_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_18_PART_1_REAL_ANTON.getAdditionalInput()[0], (int) DAY_18_PART_1_REAL_ANTON.getAdditionalInput()[1]));
    }

    @Benchmark
    public void day18PartTwoAnton(Blackhole bh) {
        bh.consume(new Day18().partTwo(Utils.parseInputByNewLine(DAY_18_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_18_PART_1_REAL_ANTON.getAdditionalInput()[0], (int) DAY_18_PART_1_REAL_ANTON.getAdditionalInput()[1]));
    }
}