package org.shvetsov.day19;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Result {
    ACCEPT("A"), REJECT("R");

    private final String sign;

    Result(String sign) {
        this.sign = sign;
    }

    private static final Map<String, Result> MAP =
            Stream.of(values())
                    .collect(toMap(res -> res.sign, e -> e));

    public static Result ofSign(String symbol) {
        return Optional.ofNullable(MAP.get(symbol)).orElseThrow();
    }

}
