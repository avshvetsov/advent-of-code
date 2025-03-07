package org.shvetsov.day17;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_17_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_17_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;


@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day17PartOneAnton(Blackhole bh) {
        bh.consume(new Day17().partOne(Utils.parseInputByNewLine(DAY_17_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_17_PART_1_REAL_ANTON.getAdditionalInput()[0]));
    }

    @Benchmark
    public void day17PartTwoAnton(Blackhole bh) {
        bh.consume(new Day17().partTwo(Utils.parseInputByNewLine(DAY_17_PART_2_REAL_ANTON.getInput(PATH)), (long) DAY_17_PART_1_REAL_ANTON.getAdditionalInput()[0]));
    }
}