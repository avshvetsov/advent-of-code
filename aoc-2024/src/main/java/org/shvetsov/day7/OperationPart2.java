package org.shvetsov.day7;

public enum OperationPart2 {
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
    CONCAT {
        @Override
        public long calculate(long x, long y) {
            String xs = String.valueOf(x);
            String ys = String.valueOf(y);
            return Long.parseLong(xs + ys);
        }
    }
    ;

    public abstract long calculate(long x, long y);
}
