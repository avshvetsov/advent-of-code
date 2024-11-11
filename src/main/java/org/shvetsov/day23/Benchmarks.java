package org.shvetsov.day23;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                           Mode  Cnt       Score   Error  Units
//Benchmarks.day23part1Anton            ss           30,396          ms/op
//Benchmarks.day23part2Anton            ss       317760,384          ms/op
//Benchmarks.day23part2AntonParallel    ss       525990,049          ms/op

@BenchmarkMode({Mode.SingleShotTime})
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day23part1Anton(Blackhole bh) {
        bh.consume(new Day23().partOneAnton(Utils.parseInputByNewLine(DAY_23_PART_1_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day23part2Anton(Blackhole bh) {
        bh.consume(new Day23().partTwoAnton(Utils.parseInputByNewLine(DAY_23_PART_2_REAL_ANTON.getInput(PATH))));
    }

    @Benchmark
    public void day23part2AntonParallel(Blackhole bh) {
        bh.consume(new Day23().partTwoAntonParallel(Utils.parseInputByNewLine(DAY_23_PART_2_REAL_ANTON.getInput(PATH))));
    }

}
