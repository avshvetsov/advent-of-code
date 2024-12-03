package org.shvetsov.day3;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_3_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_3_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                    Mode  Cnt  Score   Error  Units
//Benchmarks.day3PartOneAnton  avgt    3  0,356 ± 0,246  ms/op
//Benchmarks.day3PartTwoAnton  avgt    3  0,829 ± 2,256  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day3PartOneAnton(Blackhole bh) {
        bh.consume(new Day3().partOne(Utils.parseInputByNewLine(DAY_3_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day3PartTwoAnton(Blackhole bh) {
        bh.consume(new Day3().partTwo(Utils.parseInputByNewLine(DAY_3_PART_2_REAL_ANTON.getInput(PATH))));
    }
}