package org.shvetsov.day8;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_8_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_8_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                    Mode  Cnt  Score   Error  Units
//Benchmarks.day8PartOneAnton  avgt    3  0,195 ± 0,341  ms/op
//Benchmarks.day8PartTwoAnton  avgt    3  0,807 ± 0,613  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day8PartOneAnton(Blackhole bh) {
        bh.consume(new Day8().partOne(Utils.parseInputByNewLine(DAY_8_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day8PartTwoAnton(Blackhole bh) {
        bh.consume(new Day8().partTwo(Utils.parseInputByNewLine(DAY_8_PART_2_REAL_ANTON.getInput(PATH))));
    }
}