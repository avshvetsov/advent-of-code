package org.shvetsov.day18;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_18_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_18_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                              Mode  Cnt  Score   Error  Units
//Benchmarks.day18part1Anton             avgt    3  0,338 ± 0,065  ms/op
//Benchmarks.day18part1AntonInefficient  avgt    3  0,604 ± 0,034  ms/op
//Benchmarks.day18part2Anton             avgt    3  0,327 ± 0,041  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day18part1AntonInefficient(Blackhole bh) {
        bh.consume(new Day18().partOneAntonInefficient(Utils.parseInputByNewLine(DAY_18_PART_1_REAL_ANTON.getInput(PATH))));
    }
    @Benchmark
    public void day18part1Anton(Blackhole bh) {
        bh.consume(new Day18().partOneAnton(Utils.parseInputByNewLine(DAY_18_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day18part2Anton(Blackhole bh) {
        bh.consume(new Day18().partTwoAnton(Utils.parseInputByNewLine(DAY_18_PART_2_REAL_ANTON.getInput(PATH))));
    }

//    @Benchmark
//    public void day188rt1Dany(Blackhole bh) {
//        bh.consume(Day18Task.part1(Utils.parseInputDany(DAY_18_PART_1_REAL_DANY.getInput(NAME))));
//    }
//
//    @Benchmark
//    public void day18part2Dany(Blackhole bh) {
//        bh.consume(Day18Task.part2(Utils.parseInputDany(DAY_18_PART_2_REAL_DANY.getInput(NAME))));
//    }

}
