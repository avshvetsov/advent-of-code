package org.shvetsov.day9;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.Utils;
import org.shvetsov.day9.dany.Day9Task;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                  Mode  Cnt  Score   Error  Units
//    Benchmarks.day9part1Anton  avgt    3  2,582 ± 0,635  ms/op
//    Benchmarks.day9part1Dany   avgt    3  2,831 ± 1,793  ms/op
//    Benchmarks.day9part2Anton  avgt    3  2,538 ± 0,526  ms/op
//    Benchmarks.day9part2Dany   avgt    3  2,820 ± 4,220  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day9part1Anton(Blackhole bh) {
        bh.consume(new Day9().partOneAnton(Utils.parseInputByNewLine(DAY_9_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day9part2Anton(Blackhole bh) {
        bh.consume(new Day9().partTwoAnton(Utils.parseInputByNewLine(DAY_9_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day9part1Dany(Blackhole bh) {
        bh.consume(Day9Task.part1(Utils.parseInputDany(DAY_9_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day9part2Dany(Blackhole bh) {
        bh.consume(Day9Task.part2(Utils.parseInputDany(DAY_9_PART_2_REAL_DANY.getInput(NAME))));
    }

}
