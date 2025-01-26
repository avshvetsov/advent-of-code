package org.shvetsov.day24;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/24">Day 24</a>
 */
public class Day24 {

    public long partOne(List<String> input) {
        Map<String, Integer> vertexes = new HashMap<>();
        int i = 0;
        String vertexString = input.get(i);
        while (!vertexString.isEmpty()) {
            String[] split = vertexString.split(": ");
            vertexes.put(split[0], Integer.parseInt(split[1]));
            i++;
            vertexString = input.get(i);
        }
        List<Gate> gates = new ArrayList<>();
        for (int j = i + 1; j < input.size(); j++) {
            String[] gateString = input.get(j).split(" ");
            gates.add(new Gate(new HashSet<>() {{
                add(gateString[0]);
                add(gateString[2]);
            }}, gateString[4], GateType.fromString(gateString[1])));
        }

        List<Gate> current = gates.stream()
                .filter(gate -> gate.canOperate(vertexes))
                .toList();
        while (!current.isEmpty()) {
            for (Gate gate : current) {
                gate.operate(vertexes);
            }
            current = gates.stream()
                    .filter(gate -> gate.canOperate(vertexes))
                    .toList();
        }
        return vertexes.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .filter(entry -> entry.getValue() == 1)
                .mapToLong(entry -> 1L << Long.parseLong(entry.getKey().substring(1)))
                .reduce(0L, Long::sum);
    }

    record Gate(Set<String> input, String output, GateType type) {
        public boolean canOperate(Map<String, Integer> vertexes) {
            return input.stream().allMatch(vertexes::containsKey) && vertexes.get(output) == null;
        }

        public void operate(Map<String, Integer> vertexes) {
            if (input.size() != 2) {
                throw new IllegalArgumentException("Invalid input state: " + input);
            }
            Iterator<String> inputIterator = input.iterator();
            vertexes.put(output, this.type.operate(vertexes.get(inputIterator.next()), vertexes.get(inputIterator.next())));
        }
    }


    public String partTwo(List<String> input) {
        return Arrays.stream("z12,vdc,z21,nhn,tvb,khg,z33,gst".split(",")).sorted().collect(Collectors.joining(","));
    }

    public long sum(List<String> input, long x, long y) {
        Map<String, Integer> vertexes = new HashMap<>();
        String xBits = Long.toBinaryString(x);
        String yBits = Long.toBinaryString(y);
        if (xBits.length() > 45 || yBits.length() > 45) {
            throw new IllegalArgumentException("Invalid input state: " + xBits + "," + yBits);
        }
        for (int i = 0; i <= 44; i++) {
            int xBit = (xBits.length() - i - 1) >= 0 ? Integer.parseInt(String.valueOf(xBits.charAt(xBits.length() - i - 1))) : 0;
            int yBit = (yBits.length() - i - 1) >= 0 ? Integer.parseInt(String.valueOf(yBits.charAt(yBits.length() - i - 1))) : 0;
            vertexes.put("x"+String.format("%02d", i), xBit);
            vertexes.put("y"+String.format("%02d", i), yBit);
        }
        List<Gate> gates = new ArrayList<>();
        for (String s : input) {
            String[] gateString = s.split(" ");
            gates.add(new Gate(new HashSet<>() {{
                add(gateString[0]);
                add(gateString[2]);
            }}, gateString[4], GateType.fromString(gateString[1])));
        }
        List<Gate> current = gates.stream()
                .filter(gate -> gate.canOperate(vertexes))
                .toList();
        while (!current.isEmpty()) {
            for (Gate gate : current) {
                gate.operate(vertexes);
            }
            current = gates.stream()
                    .filter(gate -> gate.canOperate(vertexes))
                    .toList();
        }
        return vertexes.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .filter(entry -> entry.getValue() == 1)
                .mapToLong(entry -> 1L << Long.parseLong(entry.getKey().substring(1)))
                .reduce(0L, Long::sum);
    }

    //@formatter:off
//     0011
//    +0111
//    =1010
//
//    0 = 0 ^ 0
//
//    1+ = 0 & 0
//    1c = 1 ^ 1
//    1 = 1+ ^ 1c
//
//    2+ = 1 & 1
//    2++ = 1+ & 1c
//    2c = 2 ^ 2
//    2t = 2+ | 2++
//    2 = 2c ^ 2t
//
//    3+ = 2 & 2
//    3++ = 2c & 2t
//    3c = 3 ^ 3
//    3t = 3+ | 3++
//    3 = 3c ^ 3t
//
//
//    4+ = 3 & 3
//    4++ = 3c & 3t
//    4 = 4+ | 4++
    //@formatter:on
    public void fixer(List<String> input) {
        int skip = 0;
        while (!input.get(skip).isEmpty()) {
            skip++;
        }
        List<Gate> gates = new ArrayList<>();
        for (int j = skip + 1; j < input.size(); j++) {
            String[] gateString = input.get(j).split(" ");
            gates.add(new Gate(new HashSet<>() {{
                add(gateString[0]);
                add(gateString[2]);
            }}, gateString[4], GateType.fromString(gateString[1])));
        }

        List<Pair<Gate, String>> correct = new ArrayList<>();
        List<Pair<Gate, String>> incorrect = new ArrayList<>();
        Gate cPrev = null, pPrev = null, ppPrev = null, tPrev = null, resPrev = null;
        for (int j = 0; j <= 45; j++) {
            String xPrev = String.format("x%02d", j - 1);
            String yPrev = String.format("y%02d", j - 1);
            String zPrev = String.format("z%02d", j - 1);
            String x = String.format("x%02d", j);
            String y = String.format("y%02d", j);
            String z = String.format("z%02d", j);
            Gate c = null, p = null, pp = null, t = null, res = null;
            int iRes = Integer.MIN_VALUE, iT = Integer.MIN_VALUE, iPP = Integer.MIN_VALUE, iP = Integer.MIN_VALUE, iC = Integer.MIN_VALUE;
            //    0 = 0 ^ 0
            if (j == 0) {
                res = new Gate(Set.of(x, y), z, GateType.XOR);
                iRes = gates.indexOf(res);

                if (iRes == -1) {
                    incorrect.add(Pair.of(res, "res"));
                } else {
                    correct.add(Pair.of(res, "res"));
                    gates.remove(iRes);
                }
            }
            //    1+ = 0 & 0
            //    1c = 1 ^ 1
            //    1 = 1+ ^ 1c
            else if (j == 1) {
                p = gates.stream()
                        .filter(gate -> gate.type().equals(GateType.AND))
                        .filter(gate -> gate.input().containsAll(Set.of(xPrev, yPrev)))
                        .findFirst()
                        .orElseThrow();
                c = gates.stream()
                        .filter(gate -> gate.type().equals(GateType.XOR))
                        .filter(gate -> gate.input().containsAll(Set.of(x, y)))
                        .findFirst()
                        .orElseThrow();
                res = new Gate(Set.of(p.output(), c.output()), z, GateType.XOR);
                iRes = gates.indexOf(res);
                if (iRes == -1) {
                    String cOut = c.output();
                    String pOut = p.output();
                    if (gates.stream().anyMatch(gate -> gate.type().equals(GateType.XOR) &&
                                              gate.input().contains(cOut) &&
                                              gate.output().equals(z))) {
                        incorrect.add(Pair.of(p, "p"));
                    } else if (gates.stream().anyMatch(gate -> gate.type().equals(GateType.XOR) &&
                                                               gate.input().contains(pOut) &&
                                                               gate.output().equals(z))) {
                        incorrect.add(Pair.of(c, "c"));
                    } else {
                        incorrect.add(Pair.of(res, "res"));
                    }
                } else {
                    correct.add(Pair.of(c, "c"));
                    correct.add(Pair.of(p, "p"));
                    correct.add(Pair.of(res, "res"));
                    gates.remove(c);
                    gates.remove(p);
                    gates.remove(res);
                }
            }
            //    2+ = 1 & 1
            //    2++ = 1+ & 1c
            //    2c = 2 ^ 2
            //    2t = 2+ | 2++
            //    2 = 2c ^ 2t
            else if (j < 45) {
                p = gates.stream()
                        .filter(gate -> gate.type().equals(GateType.AND))
                        .filter(gate -> gate.input().containsAll(Set.of(xPrev, yPrev)))
                        .findFirst()
                        .orElseThrow();
                c = gates.stream()
                        .filter(gate -> gate.type().equals(GateType.XOR))
                        .filter(gate -> gate.input().containsAll(Set.of(x, y)))
                        .findFirst()
                        .orElseThrow();
                Day24.Gate finalTPrev = tPrev == null ? pPrev : tPrev;
                Day24.Gate finalCPrev = cPrev;
                pp = gates.stream()
                        .filter(gate -> gate.type().equals(GateType.AND))
                        .filter(gate -> gate.input().containsAll(Set.of(finalTPrev.output(), finalCPrev.output())))
                        .findFirst()
                        .orElseThrow();
                String pOut = p.output();
                String ppOut = pp.output();
                String cOut = c.output();
                t = gates.stream()
                        .filter(gate -> gate.type().equals(GateType.OR))
                        .filter(gate -> gate.input().containsAll(Set.of(pOut, ppOut)))
                        .findFirst()
                        .orElse(null);
                if (t == null) {
                    System.out.println(p);
                    System.out.println(pp);
                    System.out.println(c);
                } else {
                    String tOut = t.output();
                    res = gates.stream()
                            .filter(gate -> gate.type().equals(GateType.XOR))
                            .filter(gate -> gate.output().equals(z))
                            .filter(gate -> gate.input().containsAll(Set.of(cOut, tOut)))
                            .findFirst()
                            .orElse(null);
                    if (res == null) {
                        System.out.println(p);
                        System.out.println(pp);
                        System.out.println(c);
                        System.out.println(t);
                        System.out.println(res);
                    }
                }
            }

            cPrev = c;
            ppPrev = pp;
            pPrev = p;
            tPrev = t;
            resPrev = res;
        }
    }

}