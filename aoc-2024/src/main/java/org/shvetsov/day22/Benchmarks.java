package org.shvetsov.day22;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_22_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_22_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt     Score      Error  Units
//Benchmarks.day22PartOneAnton  avgt    3    24,625 ±   38,303  ms/op
//Benchmarks.day22PartTwoAnton  avgt    3  2412,323 ± 1043,418  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 20)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day22PartOneAnton(Blackhole bh) {
        bh.consume(new Day22().partOne(Utils.parseInputByNewLine(DAY_22_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_22_PART_1_REAL_ANTON.getAdditionalInput()[0]));
    }

    @Benchmark
    public void day22PartTwoAnton(Blackhole bh) {
        bh.consume(new Day22().partTwo(Utils.parseInputByNewLine(DAY_22_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_22_PART_2_REAL_ANTON.getAdditionalInput()[0]));
    }
}