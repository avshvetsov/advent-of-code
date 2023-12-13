package org.shvetsov.day5;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.Utils;

import java.util.concurrent.TimeUnit;

//    Benchmark                  Mode  Cnt  Score   Error  Units
//    Benchmarks.day5part1Anton  avgt    3  0,317 ± 0,049  ms/op
//    Benchmarks.day5part1Dany   avgt    3  0,431 ± 0,045  ms/op
//    Benchmarks.day5part2Anton  avgt    3  0,479 ± 0,022  ms/op
//    Benchmarks.day5part2Dany   avgt    3  1,964 ± 0,130  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day5part1Anton(Blackhole bh) {
        bh.consume(new Day5().partOneAnton(Utils.parseInputByNewLine(Day5.INPUT_PATH)));
    }

    @Benchmark
    public void day5part2Anton(Blackhole bh) {
        bh.consume(new Day5().partTwoAnton(Utils.parseInputByNewLine(Day5.INPUT_PATH)));
    }

    @Benchmark
    public void day5part1Dany(Blackhole bh) {
        bh.consume(Day5Dany.part1(Utils.parseInputDany(Day5Dany.INPUT_NAME)));
    }

    @Benchmark
    public void day5part2Dany(Blackhole bh) {
        bh.consume(Day5Dany.part2(Utils.parseInputDany(Day5Dany.INPUT_NAME)));
    }

}
