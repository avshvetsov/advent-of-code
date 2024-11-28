package org.shvetsov.day11;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@Getter
@Setter
public class MonkeyBigDecimalDebug {

    private int id;
    private List<BigInteger> itemWorries = new ArrayList<>();
    private UnaryOperator<BigInteger> operation;
    private Predicate<BigInteger> condition;
    private BiConsumer<BigInteger, List<MonkeyBigDecimalDebug>> trueAction;
    private BiConsumer<BigInteger, List<MonkeyBigDecimalDebug>> falseAction;
    private int observationCount;

    public void process(List<MonkeyBigDecimalDebug> monkeys) {
        while (!itemWorries.isEmpty()) {
            BigInteger worry = itemWorries.removeFirst();
            BigInteger newWorry = operation.apply(worry);
            if (condition.test(newWorry)) {
                trueAction.accept(newWorry, monkeys);
            } else {
                falseAction.accept(newWorry, monkeys);
            }
            observationCount++;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(observationCount);
    }

}
