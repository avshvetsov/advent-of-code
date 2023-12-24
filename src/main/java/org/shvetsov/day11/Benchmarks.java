package org.shvetsov.day11;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.day11.dany.Day11Task;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;


//    Benchmark                   Mode  Cnt   Score   Error  Units
//    Benchmarks.day11part1Anton  avgt    3   1,721 ± 0,118  ms/op
//    Benchmarks.day11part1Dany   avgt    3  23,382 ± 2,152  ms/op
//    Benchmarks.day11part2Anton  avgt    3  57,605 ± 2,674  ms/op
//    Benchmarks.day11part2Dany   avgt    3  22,832 ± 1,127  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day11part1Anton(Blackhole bh) {
        bh.consume(new Day11().partOneAnton(Utils.parseInputByNewLine(DAY_11_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day11part2Anton(Blackhole bh) {
        bh.consume(new Day11().partTwoAnton(Utils.parseInputByNewLine(DAY_11_PART_2_REAL_ANTON.getInput(PATH)), (Integer) DAY_11_PART_2_REAL_ANTON.getAdditionalInput()));
    }

    @Benchmark
    public void day11part1Dany(Blackhole bh) {
        bh.consume(Day11Task.part1(Utils.parseInputDany(DAY_11_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day11part2Dany(Blackhole bh) {
        bh.consume(Day11Task.part2(Utils.parseInputDany(DAY_11_PART_2_REAL_DANY.getInput(NAME))));
    }

}
