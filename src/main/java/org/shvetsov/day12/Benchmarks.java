package org.shvetsov.day12;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                   Mode  Cnt   Score   Error  Units
//    Benchmarks.day12part1Anton  avgt    3   4,397 ± 0,244  ms/op
//    Benchmarks.day12part2Anton  avgt    3  68,422 ± 3,664  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day12part1Anton(Blackhole bh) {
        bh.consume(new Day12().partOneAnton(Utils.parseInputByNewLine(DAY_12_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day12part2Anton(Blackhole bh) {
        bh.consume(new Day12().partTwoAnton(Utils.parseInputByNewLine(DAY_12_PART_2_REAL_ANTON.getInput(PATH))));
    }

//    @Benchmark
//    public void day11part1Dany(Blackhole bh) {
//        bh.consume(Day12Task.part1(Utils.parseInputDany(DAY_12_PART_1_REAL_DANY.getInput(NAME))));
//    }
//
//    @Benchmark
//    public void day11part2Dany(Blackhole bh) {
//        bh.consume(Day12Task.part2(Utils.parseInputDany(DAY_12_PART_2_REAL_DANY.getInput(NAME))));
//    }

}
