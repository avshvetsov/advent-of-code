package org.shvetsov.day17;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;



@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
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
