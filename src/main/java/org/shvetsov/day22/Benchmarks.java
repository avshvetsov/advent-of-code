package org.shvetsov.day22;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;


@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day22part1Anton(Blackhole bh) {
        bh.consume(new Day22().partOneAnton(Utils.parseInputByNewLine(DAY_22_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day22part2Anton(Blackhole bh) {
        bh.consume(new Day22().partTwoAnton(Utils.parseInputByNewLine(DAY_22_PART_2_REAL_ANTON.getInput(PATH))));
    }

}
