package org.shvetsov.day8;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;
import org.shvetsov.day8.dany.Day8Dany;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.NAME;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//    Benchmark                  Mode  Cnt  Score   Error  Units
//    Benchmarks.day8part1Anton  avgt    3  0,638 ± 0,083  ms/op
//    Benchmarks.day8part1Dany   avgt    3  0,962 ± 0,062  ms/op
//    Benchmarks.day8part2Anton  avgt    3  1,922 ± 0,053  ms/op
//    Benchmarks.day8part2Dany   avgt    3  7,984 ± 2,176  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 3, time = 10)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day8part1Anton(Blackhole bh) {
        bh.consume(new Day8().partOneAnton(Utils.parseInputByNewLine(DAY_8_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day8part2Anton(Blackhole bh) {
        bh.consume(new Day8().partTwoAnton(Utils.parseInputByNewLine(DAY_8_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day8part1Dany(Blackhole bh) {
        bh.consume(Day8Dany.part1(Utils.parseInputDany(DAY_8_PART_1_REAL_DANY.getInput(NAME))));
    }

    @Benchmark
    public void day8part2Dany(Blackhole bh) {
        bh.consume(Day8Dany.part2(Utils.parseInputDany(DAY_8_PART_2_REAL_DANY.getInput(NAME))));
    }

}
