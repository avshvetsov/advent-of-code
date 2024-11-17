package org.shvetsov.day25;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.shvetsov.utils.Utils;

import java.util.concurrent.TimeUnit;

import static org.shvetsov.InputAnswer.*;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

//Benchmark                   (monteCarloSize)  Mode  Cnt     Score       Error  Units
//Benchmarks.day25part1Anton                10  avgt    3  1864,413 ± 20418,555  ms/op
//Benchmarks.day25part1Anton                20  avgt    3   130,236 ±   299,858  ms/op
//Benchmarks.day25part1Anton                30  avgt    3    77,209 ±    86,003  ms/op
//Benchmarks.day25part1Anton                50  avgt    3    54,166 ±   102,841  ms/op
//Benchmarks.day25part1Anton               100  avgt    3    68,752 ±    74,801  ms/op
//Benchmarks.day25part1Anton               200  avgt    3   102,288 ±    47,856  ms/op
//Benchmarks.day25part1Anton               500  avgt    3   231,963 ±    51,620  ms/op
//Benchmarks.day25part1Anton              1000  avgt    3   470,189 ±    30,113  ms/op

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 5)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {

    @Benchmark
    public void day25part1Anton(Blackhole bh, StateDay25 state) {
        bh.consume(new Day25().partOneAnton(Utils.parseInputByNewLine(DAY_25_PART_1_REAL_ANTON.getInput(PATH)), state.monteCarloSize));
    }

    @State(Scope.Benchmark)
    public static class StateDay25 {
        @Param({ "10", "20", "30", "50", "100", "200", "500", "1000"})
        public int monteCarloSize;
    }

}
