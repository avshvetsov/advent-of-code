package org.shvetsov.day7;

public enum OperationPart1 {
    PLUS {
        @Override
        public long calculate(long x, long y) {
            return x + y;
        }
    },
    MULTIPLY {
        @Override
        public long calculate(long x, long y) {
            return x * y;
        }
    },
    ;

    public abstract long calculate(long x, long y);
}
