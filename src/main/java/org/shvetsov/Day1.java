package org.shvetsov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Day1 {

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine("src/main/resources/day 1-1.txt");
        Day1 day = new Day1();
        System.out.println(day.task1_1(input));
    }

    private int task1_1(List<String> input) {
        int sum = 0;
        for (String word :
                input) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (Character.isDigit(ch)) {
                    sum = sum + Character.getNumericValue(ch) * 10;
                    break;
                }
            }
            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);
                if (Character.isDigit(ch)) {
                    sum = sum + Character.getNumericValue(ch);
                    break;
                }
            }
        }
        return sum;
    }



    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1, time = 5)
    @Measurement(iterations = 3, time = 5)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void measureName(Blackhole bh) {
        bh.consume(task1_1(Utils.parseInputByNewLine("src/main/resources/day 1-1.txt")));
    }
}
