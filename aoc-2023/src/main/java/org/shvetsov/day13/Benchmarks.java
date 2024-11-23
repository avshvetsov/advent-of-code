package org.shvetsov.day13;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.day13.dany.Day13Task;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                   Mode  Cnt  Score   Error  Units
//    Benchmarks.day13part1Anton  avgt    3  0,355 ± 0,103  ms/op
//    Benchmarks.day13part1Dany   avgt    3  0,604 ± 0,179  ms/op
//    Benchmarks.day13part2Anton  avgt    3  0,371 ± 0,008  ms/op
//    Benchmarks.day13part2Dany   avgt    3  0,612 ± 0,131  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day13part1Anton(Blackhole bh) {
        bh.consume(new Day13().partOneAnton(Utils.parseInputByNewLine(DAY_13_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day13part2Anton(Blackhole bh) {
        bh.consume(new Day13().partTwoAnton(Utils.parseInputByNewLine(DAY_13_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day13part1Dany(Blackhole bh) {
        bh.consume(Day13Task.part1(Utils.parseInputDany(DAY_13_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day13part2Dany(Blackhole bh) {
        bh.consume(Day13Task.part2(Utils.parseInputDany(DAY_13_PART_2_REAL_DANY.getInput(NAME))));
    }

}
