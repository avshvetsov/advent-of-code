package org.shvetsov.day16;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                   Mode  Cnt    Score    Error  Units
//    Benchmarks.day16part1Anton  avgt    3    1,804 ±  0,358  ms/op
//    Benchmarks.day16part2Anton  avgt    3  332,884 ± 24,859  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day16part1Anton(Blackhole bh) {
        bh.consume(new Day16().partOneAnton(Utils.parseInputByNewLine(DAY_16_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day16part2Anton(Blackhole bh) {
        bh.consume(new Day16().partTwoAnton(Utils.parseInputByNewLine(DAY_16_PART_2_REAL_ANTON.getInput(PATH))));
    }

//    @Benchmark
//    public void day16part1Dany(Blackhole bh) {
//        bh.consume(Day16Task.part1(Utils.parseInputDany(DAY_16_PART_1_REAL_DANY.getInput(NAME))));
//    }
//
//    @Benchmark
//    public void day16part2Dany(Blackhole bh) {
//        bh.consume(Day16Task.part2(Utils.parseInputDany(DAY_16_PART_2_REAL_DANY.getInput(NAME))));
//    }

}
