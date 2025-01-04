package org.shvetsov.day16;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_16_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_16_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt    Score   Error  Units
//Benchmarks.day16PartOneAnton  avgt    3   15,380 ± 0,835  ms/op
//Benchmarks.day16PartTwoAnton  avgt    3  117,771 ± 4,321  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day16PartOneAnton(Blackhole bh) {
        bh.consume(new Day16().partOne(Utils.parseInputByNewLine(DAY_16_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day16PartTwoAnton(Blackhole bh) {
        bh.consume(new Day16().partTwo(Utils.parseInputByNewLine(DAY_16_PART_2_REAL_ANTON.getInput(PATH))));
    }
}