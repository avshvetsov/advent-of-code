package org.shvetsov.day10;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.day10.dany.Day10Task;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                   Mode  Cnt     Score     Error  Units
//    Benchmarks.day10part1Anton  avgt    3     0,461 ±   0,063  ms/op
//    Benchmarks.day10part1Dany   avgt    3     2,444 ±   0,198  ms/op
//    Benchmarks.day10part2Anton  avgt    3     5,192 ±   0,424  ms/op
//    Benchmarks.day10part2Dany   avgt    3  1154,966 ± 590,542  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day10part1Anton(Blackhole bh) {
        bh.consume(new Day10().partOneAnton(Utils.parseInputByNewLine(DAY_10_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day10part2Anton(Blackhole bh) {
        bh.consume(new Day10().partTwoAnton(Utils.parseInputByNewLine(DAY_10_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day10part1Dany(Blackhole bh) {
        bh.consume(Day10Task.part1(Utils.parseInputDany(DAY_10_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day10part2Dany(Blackhole bh) {
        bh.consume(Day10Task.part2(Utils.parseInputDany(DAY_10_PART_2_REAL_DANY.getInput(NAME))));
    }

}
