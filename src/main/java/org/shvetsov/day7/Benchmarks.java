package org.shvetsov.day7;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.Utils;
import org.shvetsov.day7.dany.Day7Dany;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                  Mode  Cnt  Score   Error  Units
//    Benchmarks.day7part1Anton  avgt    3  1,096 ± 0,299  ms/op
//    Benchmarks.day7part1Dany   avgt    3  6,279 ± 1,595  ms/op
//    Benchmarks.day7part2Anton  avgt    3  1,311 ± 0,115  ms/op
//    Benchmarks.day7part2Dany   avgt    3  6,903 ± 2,083  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day7part1Anton(Blackhole bh) {
        bh.consume(new Day7().partOneAnton(Utils.parseInputByNewLine(DAY_7_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day7part2Anton(Blackhole bh) {
        bh.consume(new Day7().partTwoAnton(Utils.parseInputByNewLine(DAY_7_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day7part1Dany(Blackhole bh) {
        bh.consume(Day7Dany.part1(Utils.parseInputDany(DAY_7_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day7part2Dany(Blackhole bh) {
        bh.consume(Day7Dany.part2(Utils.parseInputDany(DAY_7_PART_2_REAL_DANY.getInput(NAME))));
    }

}
