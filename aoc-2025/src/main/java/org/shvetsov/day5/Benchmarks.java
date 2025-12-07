package org.shvetsov.day5;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_5_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_5_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day5PartOneAnton(Blackhole bh) {
        bh.consume(new Day5().partOne(Utils.parseInputByNewLine(DAY_5_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day5PartTwoAnton(Blackhole bh) {
        bh.consume(new Day5().partTwo(Utils.parseInputByNewLine(DAY_5_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day5PartOneTreeMapAnton(Blackhole bh) {
        bh.consume(new Day5().partOneTreeMap(Utils.parseInputByNewLine(DAY_5_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day5PartTwoTreeMapAnton(Blackhole bh) {
        bh.consume(new Day5().partTwoTreeMap(Utils.parseInputByNewLine(DAY_5_PART_2_REAL_ANTON.getInput(PATH))));
    }
}
