package org.shvetsov.day20;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_20_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_20_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                   Mode  Cnt   Score   Error  Units
//Benchmarks.day20part1Anton  avgt    3   2,691 ± 0,542  ms/op
//Benchmarks.day20part2Anton  avgt    3  10,751 ± 0,404  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day20part1Anton(Blackhole bh) {
        bh.consume(new Day20().partOneAnton(Utils.parseInputByNewLine(DAY_20_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day20part2Anton(Blackhole bh) {
        bh.consume(new Day20().partTwoAnton(Utils.parseInputByNewLine(DAY_20_PART_2_REAL_ANTON.getInput(PATH))));
    }

}
