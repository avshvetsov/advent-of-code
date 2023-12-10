package org.shvetsov.day1;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.Utils;

import java.util.concurrent.TimeUnit;

//    RESULTS:
//
//        Benchmark                 Mode  Cnt  Score   Error  Units
//        Benchmarks.day1part1      avgt    3  0,343 ± 0,255  ms/op
//        Benchmarks.day1part1Alex  avgt    3  0,993 ± 0,060  ms/op
//        Benchmarks.day1part1Dany  avgt    3  0,397 ± 0,108  ms/op
//        Benchmarks.day1part2      avgt    3  1,406 ± 0,078  ms/op
//        Benchmarks.day1part2Dany  avgt    3  1,511 ± 0,194  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    public static final String INPUT_PATH = "src/main/resources/input 1-1, 1-2.txt";

    @Benchmark
    public void day1part1(Blackhole bh) {
        bh.consume(new Day1().partOneTony(Utils.parseInputByNewLine(INPUT_PATH)));
    }

    @Benchmark
    public void day1part1Alex(Blackhole bh) {
        bh.consume(new Day1().partOneAlex());
    }
    @Benchmark
    public void day1part1Dany(Blackhole bh) {
        bh.consume(Day1.partOneDany(Utils.parseInputByNewLine(INPUT_PATH).toArray(String[]::new)));
    }

    @Benchmark
    public void day1part2(Blackhole bh) {
        bh.consume(new Day1().partTwoTony(Utils.parseInputByNewLine(INPUT_PATH)));
    }

    @Benchmark
    public void day1part2Dany(Blackhole bh) {
        bh.consume(Day1.partTwoDany(Utils.parseInputByNewLine(INPUT_PATH).toArray(String[]::new)));
    }
}
