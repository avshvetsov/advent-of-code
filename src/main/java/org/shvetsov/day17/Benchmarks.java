package org.shvetsov.day17;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;


//    Benchmark                   Mode  Cnt     Score      Error  Units
//    Benchmarks.day17part1Anton  avgt    3   435,354 ±  104,109  ms/op
//    Benchmarks.day17part2Anton  avgt    3  1693,824 ± 3788,144  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day17part1Anton(Blackhole bh) {
        bh.consume(new Day17().partOneAnton(Utils.parseInputByNewLine(DAY_17_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day17part2Anton(Blackhole bh) {
        bh.consume(new Day17().partTwoAnton(Utils.parseInputByNewLine(DAY_17_PART_2_REAL_ANTON.getInput(PATH))));
    }

//    @Benchmark
//    public void day17part1Dany(Blackhole bh) {
//        bh.consume(Day17Task.part1(Utils.parseInputDany(DAY_17_PART_1_REAL_DANY.getInput(NAME))));
//    }
//
//    @Benchmark
//    public void day17part2Dany(Blackhole bh) {
//        bh.consume(Day17Task.part2(Utils.parseInputDany(DAY_17_PART_2_REAL_DANY.getInput(NAME))));
//    }

}
