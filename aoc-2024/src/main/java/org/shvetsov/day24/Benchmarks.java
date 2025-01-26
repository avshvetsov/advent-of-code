package org.shvetsov.day24;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_24_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_24_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt  Score   Error  Units
//Benchmarks.day24PartOneAnton  avgt    3  1,753 ± 2,368  ms/op
//Benchmarks.day24PartTwoAnton  avgt    3  0,091 ± 0,034  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day24PartOneAnton(Blackhole bh) {
        bh.consume(new Day24().partOne(Utils.parseInputByNewLine(DAY_24_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day24PartTwoAnton(Blackhole bh) {
        bh.consume(new Day24().partTwo(Utils.parseInputByNewLine(DAY_24_PART_2_REAL_ANTON.getInput(PATH))));
    }
}