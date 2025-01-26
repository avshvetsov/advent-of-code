package org.shvetsov.day24;

public enum GateType {
    AND{
        @Override
        public int operate(int l, int r) {
            return l & r;
        }
    },
    OR{
        @Override
        public int operate(int l, int r) {
            return l | r;
        }
    },
    XOR{
        @Override
        public int operate(int l, int r) {
            return l ^ r;
        }
    };

    public abstract int operate(int l, int r);

    public static GateType fromString(String s) {
        return switch (s) {
            case "AND" -> GateType.AND;
            case "OR" -> GateType.OR;
            case "XOR" -> GateType.XOR;
            default -> throw new IllegalArgumentException("Unknown gate type: " + s);
        };
    }
}
