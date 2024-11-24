package org.shvetsov.day4;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_4_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_4_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                    Mode  Cnt  Score   Error  Units
//Benchmarks.day4PartOneAnton  avgt    3  0,323 ± 0,250  ms/op
//Benchmarks.day4PartTwoAnton  avgt    3  0,303 ± 0,135  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day4PartOneAnton(Blackhole bh) {
        bh.consume(new Day4().partOne(Utils.parseInputByNewLine(DAY_4_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day4PartTwoAnton(Blackhole bh) {
        bh.consume(new Day4().partTwo(Utils.parseInputByNewLine(DAY_4_PART_2_REAL_ANTON.getInput(PATH))));
    }
}