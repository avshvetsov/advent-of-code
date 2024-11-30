package org.shvetsov.day13;

import java.util.ArrayList;
import java.util.List;

sealed public interface Packet permits IntegerValue, ListValues {
    int compare(Packet packet);
    int size();
    Packet get(int index);

    static Packet buildPacket(String s) {
        List<Packet> packet = new ArrayList<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            Character c = charArray[i];
            switch (c) {
                case '[' -> {
                    int end = i + findCloseBracket(s.substring(i));
                    packet.add(buildPacket(s.substring(i + 1, end)));
                    i = end;
                }
                case Character ch when Character.isDigit(ch) -> {
                    int end = s.indexOf(',', i);
                    if (end == -1) {
                        end = charArray.length;
                    }
                    packet.add(new IntegerValue(Integer.parseInt(s.substring(i, end))));
                    i = end;
                }
                case ',', ']' -> {
                    //ignore
                }
                default -> throw new IllegalStateException("Unexpected value: " + c);
            }
        }
        return new ListValues(packet);
    }

    static private int findCloseBracket(String s) {
        if (!s.startsWith("[")) {
            throw new IllegalStateException("Should start with [");
        }
        int i = 0;
        int count = 0;
        do {
            if (s.charAt(i) == '[') {
                count++;
            } else if (s.charAt(i) == ']') {
                count--;
            }
            i++;
        } while (count != 0);

        return i - 1;
    }
}
