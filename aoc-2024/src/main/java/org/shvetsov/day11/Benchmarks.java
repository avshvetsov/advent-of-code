package org.shvetsov.day11;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_11_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_11_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt   Score   Error  Units
//Benchmarks.day11PartOneAnton  avgt    3  22,743 ± 3,002  ms/op
//Benchmarks.day11PartTwoAnton  avgt    3  17,098 ± 3,778  ms/op
//Benchmarks.day11PartTwoDany   avgt    3  35,216 ± 9,444  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day11PartOneAnton(Blackhole bh) {
        bh.consume(new Day11().partOne(Utils.parseInputByNewLine(DAY_11_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_11_PART_1_REAL_ANTON.getAdditionalInput()));
    }

    @Benchmark
    public void day11PartTwoAnton(Blackhole bh) {
        bh.consume(new Day11().partTwo(Utils.parseInputByNewLine(DAY_11_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_11_PART_2_REAL_ANTON.getAdditionalInput()));
    }

    @Benchmark
    public void day11PartTwoDany(Blackhole bh) {
        bh.consume(new Day11().partTwoDany(Utils.parseInputByNewLine(DAY_11_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_11_PART_2_REAL_ANTON.getAdditionalInput()));
    }
}