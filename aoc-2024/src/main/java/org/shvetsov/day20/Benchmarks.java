package org.shvetsov.day20;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.DAY_20_PART_1_REAL_ANTON;
import static org.shvetsov.InputAnswer.DAY_20_PART_2_REAL_ANTON;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                            Mode  Cnt   Score   Error  Units
//Benchmarks.day20PartOneAnton         avgt    3   1,295 ± 0,192  ms/op
//Benchmarks.day20PartOneGeneralAnton  avgt    3   1,166 ± 0,084  ms/op
//Benchmarks.day20PartTwoAnton         avgt    3  18,672 ± 0,525  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day20PartOneAnton(Blackhole bh) {
        bh.consume(new Day20().partOne(Utils.parseInputByNewLine(DAY_20_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_20_PART_1_REAL_ANTON.getAdditionalInput()[0]));
    }

    @Benchmark
    public void day20PartOneGeneralAnton(Blackhole bh) {
        bh.consume(new Day20().partOneGeneral(Utils.parseInputByNewLine(DAY_20_PART_1_REAL_ANTON.getInput(PATH)), (int) DAY_20_PART_1_REAL_ANTON.getAdditionalInput()[0], (int) DAY_20_PART_1_REAL_ANTON.getAdditionalInput()[1]));
    }

    @Benchmark
    public void day20PartTwoAnton(Blackhole bh) {
        bh.consume(new Day20().partTwo(Utils.parseInputByNewLine(DAY_20_PART_2_REAL_ANTON.getInput(PATH)), (int) DAY_20_PART_2_REAL_ANTON.getAdditionalInput()[0], (int) DAY_20_PART_2_REAL_ANTON.getAdditionalInput()[1]));
    }
}