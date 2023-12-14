package org.shvetsov.day6;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.Utils;

import java.util.concurrent.TimeUnit;

//    Benchmark                  Mode  Cnt     Score     Error  Units
//    Benchmarks.day6part1Anton  avgt    3     0,296 ±   0,078  ms/op
//    Benchmarks.day6part1Dany   avgt    3     0,371 ±   0,028  ms/op
//    Benchmarks.day6part2Anton  avgt    3     0,296 ±   0,023  ms/op
//    Benchmarks.day6part2Dany   avgt    3  1263,422 ± 594,819  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day6part1Anton(Blackhole bh) {
        bh.consume(new Day6().partOneAnton(Utils.parseInputByNewLine(Day6.INPUT_PATH)));
    }

    @Benchmark
    public void day6part2Anton(Blackhole bh) {
        bh.consume(new Day6().partTwoAnton(Utils.parseInputByNewLine(Day6.INPUT_PATH)));
    }

    @Benchmark
    public void day6part1Dany(Blackhole bh) {
        bh.consume(Day6Dany.part1(Utils.parseInputDany(Day6Dany.INPUT_NAME)));
    }

    @Benchmark
    public void day6part2Dany(Blackhole bh) {
        bh.consume(Day6Dany.part2(Utils.parseInputDany(Day6Dany.INPUT_NAME)));
    }

}
