package org.shvetsov.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://adventofcode.com/2022/day/13">Day 13</a>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Day13Ugly {

    public long partOne(List<String> input) {
        int result = 0;
        for (int i = 0, j = 1; i < input.size(); i += 3, j++) {
            List<Object> packet1 = buildPacket(input.get(i));
            List<Object> packet2 = buildPacket(input.get(i + 1));
            if (comparePacket(packet1, packet2) == -1) {
                result += j;
            }
        }
        return result;
    }

    //return 0 when packet - equals
    //return 1 when packet1 > packet2
    //return -1 when packet1 < packet2
    private int comparePacket(List<Object> packet1, List<Object> packet2) {
        int size = Math.min(packet1.size(), packet2.size());
        for (int i = 0; i < size; i++) {
            Object o1 = packet1.get(i);
            Object o2 = packet2.get(i);
            if (o1 instanceof Integer i1 && o2 instanceof Integer i2) {
                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                }
            } else if (o1 instanceof List l1 && o2 instanceof List l2) {
                int res = comparePacket(l1, l2);
                if (res != 0) {
                    return res;
                }
            } else if (o1 instanceof Integer i1 && o2 instanceof List l2) {
                int res = comparePacket(List.of(i1), l2);
                if (res != 0) {
                    return res;
                }
            } else if (o1 instanceof List l1 && o2 instanceof Integer i2) {
                int res = comparePacket(l1, List.of(i2));
                if (res != 0) {
                    return res;
                }
            }
        }
        return Integer.compare(packet1.size(), packet2.size());
    }

    private List<Object> buildPacket(String s) {
        List<Object> packet = new ArrayList<>();
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
                    packet.add(Integer.parseInt(s.substring(i, end)));
                    i = end;
                }
                case ',', ']' -> {
                    //ignore
                }
                default -> throw new IllegalStateException("Unexpected value: " + c);
            }
        }
        return packet;
    }

    private int findCloseBracket(String s) {
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


    public long partTwo(List<String> input) {
        Map<String, List> packets = new HashMap<>();
        packets.put("[[6]]", buildPacket("[[6]]"));
        packets.put("[[2]]", buildPacket("[[2]]"));
        for (String s : input) {
            if (!s.isEmpty()) {
                packets.put(s, buildPacket(s));
            }
        }
        List<Map.Entry<String, List>> sorted = packets.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(this::comparePacket))
                .toList();
        int result = 1;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i).getKey().equals("[[2]]") || sorted.get(i).getKey().equals("[[6]]")) {
                result *= i + 1;
            }
        }
        return result;
    }

}