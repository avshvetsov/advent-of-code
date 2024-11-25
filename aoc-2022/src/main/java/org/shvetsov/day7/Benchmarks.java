package org.shvetsov.day7;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_7_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_7_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                    Mode  Cnt  Score   Error  Units
//Benchmarks.day7PartOneAnton  avgt    3  0,349 ± 0,723  ms/op
//Benchmarks.day7PartTwoAnton  avgt    3  0,321 ± 0,137  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day7PartOneAnton(Blackhole bh) {
        bh.consume(new Day7().partOne(Utils.parseInputByNewLine(DAY_7_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day7PartTwoAnton(Blackhole bh) {
        bh.consume(new Day7().partTwo(Utils.parseInputByNewLine(DAY_7_PART_2_REAL_ANTON.getInput(PATH))));
    }
}