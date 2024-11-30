package org.shvetsov.day13;

public record IntegerValue(Integer value) implements Packet {

    @Override
    public int compare(Packet packet) {
        switch (packet) {
            case IntegerValue(Integer i2) -> {
                int res = Integer.compare(value, i2);
                if (res != 0) {
                    return res;
                }
            }
            case ListValues l2 -> {
                int res = -1 * l2.compare(this);
                if (res != 0) {
                    return res;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + packet);
        }
        return Integer.compare(this.size(), packet.size());
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Packet get(int index) {
        return this;
    }

}
