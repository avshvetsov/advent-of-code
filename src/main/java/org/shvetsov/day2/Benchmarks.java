package org.shvetsov.day2;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.Utils;

import java.util.concurrent.TimeUnit;

//    RESULTS:
//
//    Benchmark                  Mode  Cnt  Score   Error  Units
//    Benchmarks.day2part1Anton  avgt    3  0,371 ± 0,033  ms/op
//    Benchmarks.day2part2Anton  avgt    3  0,374 ± 0,106  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day2part1Anton(Blackhole bh) {
        bh.consume(new Day2().partOneAnton(Utils.parseInputByNewLine(Day2.INPUT_PATH)));
    }

    @Benchmark
    public void day2part2Anton(Blackhole bh) {
        bh.consume(new Day2().partTwoAnton(Utils.parseInputByNewLine(Day2.INPUT_PATH)));
    }

}
