package org.shvetsov.day24;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_24_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_24_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                   Mode  Cnt   Score   Error  Units
//Benchmarks.day24part1Anton  avgt    3   1,351 ± 0,342  ms/op
//Benchmarks.day24part2Anton  avgt    3  44,103 ± 1,428  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day24part1Anton(Blackhole bh) {
        bh.consume(new Day24().partOneAnton(Utils.parseInputByNewLine(DAY_24_PART_1_REAL_ANTON.getInput(PATH)), (long[]) DAY_24_PART_1_REAL_ANTON.getAdditionalInput()));
    }

    @Benchmark
    public void day24part2Anton(Blackhole bh) {
        bh.consume(new Day24().partTwoAnton(Utils.parseInputByNewLine(DAY_24_PART_2_REAL_ANTON.getInput(PATH))));
    }

}
