package org.shvetsov.day13;

import java.util.List;

public  record ListValues(List<Packet> allValues) implements Packet {

    @Override
    public int compare(Packet packet) {
        int size = Math.min(this.size(), packet.size());
        for (int i = 0; i < size; i++) {
            Packet target = packet.get(i);
            int res = this.get(i).compare(target);
            if (res != 0) {
                return res;
            }
        }
        return Integer.compare(this.size(), packet.size());
    }

    @Override
    public int size() {
        return allValues.size();
    }

    @Override
    public Packet get(int index) {
        return allValues.get(index);
    }

}
