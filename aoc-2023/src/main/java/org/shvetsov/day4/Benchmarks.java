package org.shvetsov.day4;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

//    RESULTS:
//
//    Benchmark                  Mode  Cnt  Score   Error  Units
//    Benchmarks.day4part1Anton  avgt    3  1,270 ± 0,269  ms/op
//    Benchmarks.day4part1Dany   avgt    3  1,183 ± 0,104  ms/op
//    Benchmarks.day4part2Anton  avgt    3  1,179 ± 0,104  ms/op
//    Benchmarks.day4part2Dany   avgt    3  1,214 ± 0,358  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day4part1Anton(Blackhole bh) {
        bh.consume(new Day4().partOneAnton(Utils.parseInputByNewLine(Day4.INPUT_PATH)));
    }

    @Benchmark
    public void day4part2Anton(Blackhole bh) {
        bh.consume(new Day4().partTwoAnton(Utils.parseInputByNewLine(Day4.INPUT_PATH)));
    }

    @Benchmark
    public void day4part1Dany(Blackhole bh) {
        bh.consume(Day4Dany.part1(Utils.parseInputDany(Day4Dany.INPUT_NAME)));
    }

    @Benchmark
    public void day4part2Dany(Blackhole bh) {
        bh.consume(Day4Dany.part2(Utils.parseInputDany(Day4Dany.INPUT_NAME)));
    }

}
