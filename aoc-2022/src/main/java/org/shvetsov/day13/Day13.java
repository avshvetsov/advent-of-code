package org.shvetsov.day13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.shvetsov.day13.Packet.buildPacket;

/**
 * <a href="https://adventofcode.com/2022/day/13">Day 13</a>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Day13 {

    public long partOne(List<String> input) {
        int result = 0;
        for (int i = 0, j = 1; i < input.size(); i += 3, j++) {
            Packet packetPacket1 = buildPacket(input.get(i));
            Packet packetPacket2 = buildPacket(input.get(i + 1));
            if (packetPacket1.compare(packetPacket2) == -1) {
                result += j;
            }
        }
        return result;
    }

    public long partTwo(List<String> input) {
        Map<String, Packet> packets = new HashMap<>();
        packets.put("[[6]]", buildPacket("[[6]]"));
        packets.put("[[2]]", buildPacket("[[2]]"));
        for (String s : input) {
            if (!s.isEmpty()) {
                packets.put(s, buildPacket(s));
            }
        }
        List<Map.Entry<String, Packet>> sorted = packets.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Packet::compare))
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