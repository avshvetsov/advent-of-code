package org.shvetsov.day19;

import lombok.Getter;

import java.util.BitSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Operand {
    LT("<", (left, right) -> left < right,
            (bitSet, val) -> {
                bitSet.set(val - 1, Constants.MAX_PART_VAL, false);
                return bitSet;
            }),
    GT(">", (left, right) -> left > right,
            (bitSet, val) -> {
                bitSet.set(0, val, false);
                return bitSet;
            }),
    LTEQ("<=", null,
            (bitSet, val) -> {
                bitSet.set(val, Constants.MAX_PART_VAL, false);
                return bitSet;
            }),
    GTEQ(">=", null,
            (bitSet, val) -> {
                bitSet.set(0, val - 1, false);
                return bitSet;
            }),
    ;


    private final String sign;
    @Getter
    private final BiFunction<Integer, Integer, Boolean> operator;
    @Getter
    private final BiFunction<BitSet, Integer, BitSet> bitSetTransformator;

    Operand(String sign, BiFunction<Integer, Integer, Boolean> operator, BiFunction<BitSet, Integer, BitSet> bitSetTransformator) {
        this.sign = sign;
        this.operator = operator;
        this.bitSetTransformator = bitSetTransformator;
    }

    private static final Map<String, Operand> MAP =
            Stream.of(values())
                    .collect(toMap(op -> op.sign, e -> e));

    public static Operand ofSign(String symbol) {
        return Optional.ofNullable(MAP.get(symbol)).orElseThrow();
    }

    public static String opposite(String sign) {
        return switch (sign) {
            case "<" -> ">=";
            case ">" -> "<=";
            case "<=" -> ">";
            case ">=" -> "<";
            case null, default -> throw new IllegalArgumentException();
        };
    }
}
