package org.shvetsov.day11;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Monkey {

    private static int DEFAULT_DIVIDER = 3;

    private int id;
    private List<Long> itemWorries = new ArrayList<>();
    private UnaryOperator<Long> operation;
    private Long testValue;
    private Predicate<Long> condition;
    private Function<List<Monkey>, Monkey> trueMonkey;
    private Function<List<Monkey>, Monkey> falseMonkey;
    private long observationCount;

    public void process(List<Monkey> monkeys, boolean useDefaultDivider) {
        while (!itemWorries.isEmpty()) {
            Long worry = itemWorries.removeFirst();
            Long newWorry = operation.apply(worry);
            if (useDefaultDivider) {
                newWorry /= DEFAULT_DIVIDER;
            }
            if (condition.test(newWorry)) {
                trueMonkey.apply(monkeys).getItemWorries().add(newWorry);
            } else {
                falseMonkey.apply(monkeys).getItemWorries().add(newWorry);
            }
            observationCount++;
        }
    }

    public void divideWorries(long lcm) {
        itemWorries = itemWorries.stream()
                .map(integer -> integer % lcm)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.valueOf(observationCount);
    }
}
