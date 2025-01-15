package org.shvetsov.day21;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_21_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_21_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                     Mode  Cnt  Score   Error  Units
//Benchmarks.day21PartOneAnton  avgt    3  0,084 ± 0,155  ms/op
//Benchmarks.day21PartTwoAnton  avgt    3  0,213 ± 0,153  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day21PartOneAnton(Blackhole bh) {
        bh.consume(new Day21().partOne(Utils.parseInputByNewLine(DAY_21_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_21_PART_1_REAL_ANTON.getAdditionalInput()[0]));
    }

    @Benchmark
    public void day21PartTwoAnton(Blackhole bh) {
        bh.consume(new Day21().partTwo(Utils.parseInputByNewLine(DAY_21_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_21_PART_2_REAL_ANTON.getAdditionalInput()[0]));
    }
}