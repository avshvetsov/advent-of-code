package org.shvetsov.day9;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_9_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_9_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                    Mode  Cnt  Score   Error  Units
//Benchmarks.day9PartOneAnton  avgt    3  1,227 ± 1,011  ms/op
//Benchmarks.day9PartTwoAnton  avgt    3  1,618 ± 0,687  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day9PartOneAnton(Blackhole bh) {
        bh.consume(new Day9().partOne(Utils.parseInputByNewLine(DAY_9_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day9PartTwoAnton(Blackhole bh) {
        bh.consume(new Day9().partTwo(Utils.parseInputByNewLine(DAY_9_PART_2_REAL_ANTON.getInput(PATH))));
    }
}