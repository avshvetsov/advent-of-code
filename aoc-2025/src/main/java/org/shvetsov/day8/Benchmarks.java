package org.shvetsov.day8;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day8PartOneAnton(Blackhole bh) {
        bh.consume(new Day8().partOne(Utils.parseInputByNewLine(DAY_8_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_8_PART_1_REAL_ANTON.getAdditionalInput()[0]));
    }

    @Benchmark
    public void day8PartTwoAnton(Blackhole bh) {
        bh.consume(new Day8().partTwo(Utils.parseInputByNewLine(DAY_8_PART_2_REAL_ANTON.getInput(PATH))));
    }
}
