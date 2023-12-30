package org.shvetsov.day15;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.day15.dany.Day15Task;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                   Mode  Cnt  Score   Error  Units
//    Benchmarks.day15part1Anton  avgt    3  0,476 ± 0,096  ms/op
//    Benchmarks.day15part1Dany   avgt    3  0,578 ± 0,064  ms/op
//    Benchmarks.day15part2Anton  avgt    3  0,775 ± 0,070  ms/op
//    Benchmarks.day15part2Dany   avgt    3  1,161 ± 0,088  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day15part1Anton(Blackhole bh) {
        bh.consume(new Day15().partOneAnton(Utils.parseInputByNewLine(DAY_15_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day15part2Anton(Blackhole bh) {
        bh.consume(new Day15().partTwoAnton(Utils.parseInputByNewLine(DAY_15_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day15part1Dany(Blackhole bh) {
        bh.consume(Day15Task.part1(Utils.parseInputDany(DAY_15_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day15part2Dany(Blackhole bh) {
        bh.consume(Day15Task.part2(Utils.parseInputDany(DAY_15_PART_2_REAL_DANY.getInput(NAME))));
    }

}
