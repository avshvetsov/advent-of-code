package org.shvetsov.day19;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_19_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_19_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                   Mode  Cnt  Score   Error  Units
//Benchmarks.day19part1Anton  avgt    3  1,086 ± 0,421  ms/op
//Benchmarks.day19part2Anton  avgt    3  1,675 ± 0,466  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day19part1Anton(Blackhole bh) {
        bh.consume(new Day19().partOneAnton(Utils.parseInputByNewLine(DAY_19_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day19part2Anton(Blackhole bh) {
        bh.consume(new Day19().partTwoAnton(Utils.parseInputByNewLine(DAY_19_PART_2_REAL_ANTON.getInput(PATH))));
    }

//    @Benchmark
//    public void day198rt1Dany(Blackhole bh) {
//        bh.consume(Day19Task.part1(Utils.parseInputDany(DAY_19_PART_1_REAL_DANY.getInput(NAME))));
//    }
//
//    @Benchmark
//    public void day19part2Dany(Blackhole bh) {
//        bh.consume(Day19Task.part2(Utils.parseInputDany(DAY_19_PART_2_REAL_DANY.getInput(NAME))));
//    }

}
