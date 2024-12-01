package org.shvetsov.day15;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_15_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_15_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                                   Mode  Cnt      Score   Error  Units
//Benchmarks.day15PartOneAnton                avgt    3      0,086 ± 0,017  ms/op
//Benchmarks.day15PartTwoAnton                  ss       15854,112          ms/op
//Benchmarks.day15PartTwoSingleRangeSetAnton    ss       37126,644          ms/op

@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @Warmup(iterations = 2, time = 3)
    @Measurement(iterations = 3, time = 5)
    public void day15PartOneAnton(Blackhole bh) {
        bh.consume(new Day15().partOne(Utils.parseInputByNewLine(DAY_15_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_15_PART_1_REAL_ANTON.getAdditionalInput()));
    }

    @Benchmark
    @BenchmarkMode({Mode.SingleShotTime})
    public void day15PartTwoAnton(Blackhole bh) {
        bh.consume(new Day15().partTwo(Utils.parseInputByNewLine(DAY_15_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_15_PART_2_REAL_ANTON.getAdditionalInput()));
    }

    @Benchmark
    @BenchmarkMode({Mode.SingleShotTime})
    public void day15PartTwoSingleRangeSetAnton(Blackhole bh) {
        bh.consume(new Day15().partTwoSingleRangeSet(Utils.parseInputByNewLine(DAY_15_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_15_PART_2_REAL_ANTON.getAdditionalInput()));
    }
}