package org.shvetsov.day8;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/8">Day 8</a>
 */
public class Day8 {

    //TODO: розібратись з тим що таке Disjoint Set і як з його допомогою вирішити цю проблему
    public long partOne(List<String> input, int connections) {
        NavigableMap<Long, Pair<Integer, Integer>> distancePair = new TreeMap<>();
        for (int i = 0; i < input.size(); i++) {
            String[] s1 = input.get(i).split(",");
            Point3D p1 = new Point3D(Integer.parseInt(s1[0]), Integer.parseInt(s1[1]), Integer.parseInt(s1[2]));
            for (int j = i + 1; j < input.size(); j++) {
                String[] s2 = input.get(j).split(",");
                Point3D p2 = new Point3D(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), Integer.parseInt(s2[2]));
                distancePair.put(p1.squareDistance(p2), Pair.of(i, j));
            }
        }

        List<Set<Integer>> circuits = new ArrayList<>();
        for (int i = 0; i < connections; i++) {
            Pair<Integer, Integer> pair = distancePair.pollFirstEntry().getValue();
            Set<Integer> leftCircuit = findCircuitWithId(circuits, pair.getLeft());
            Set<Integer> rightCircuit = findCircuitWithId(circuits, pair.getRight());
            if (leftCircuit != null && rightCircuit != null) {
                if (leftCircuit != rightCircuit) {
                    leftCircuit.addAll(rightCircuit);
                    circuits.remove(rightCircuit);
                }
            } else if (leftCircuit != null) {
                leftCircuit.add(pair.getRight());
            } else if (rightCircuit != null) {
                rightCircuit.add(pair.getLeft());
            } else {
                HashSet<Integer> circuit = new HashSet<>();
                circuit.add(pair.getLeft());
                circuit.add(pair.getRight());
                circuits.add(circuit);
            }
        }
        return circuits.stream()
                .map(Set::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce((i1, i2) -> i1 * i2)
                .orElse(0);
    }

    private Set<Integer> findCircuitWithId(List<Set<Integer>> circuits, Integer id) {
        for (Set<Integer> circuit : circuits) {
            if (circuit.contains(id)) {
                return circuit;
            }
        }
        return null;
    }

    public long partTwo(List<String> input) {
        NavigableMap<Long, Pair<Integer, Integer>> distancePair = new TreeMap<>();
        for (int i = 0; i < input.size(); i++) {
            String[] s1 = input.get(i).split(",");
            Point3D p1 = new Point3D(Integer.parseInt(s1[0]), Integer.parseInt(s1[1]), Integer.parseInt(s1[2]));
            for (int j = i + 1; j < input.size(); j++) {
                String[] s2 = input.get(j).split(",");
                Point3D p2 = new Point3D(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), Integer.parseInt(s2[2]));
                distancePair.put(p1.squareDistance(p2), Pair.of(i, j));
            }
        }

        List<Set<Integer>> circuits = new ArrayList<>();
        Pair<Integer, Integer> pair;
        do {
            pair = distancePair.pollFirstEntry().getValue();
            Set<Integer> leftCircuit = findCircuitWithId(circuits, pair.getLeft());
            Set<Integer> rightCircuit = findCircuitWithId(circuits, pair.getRight());
            if (leftCircuit != null && rightCircuit != null) {
                if (leftCircuit != rightCircuit) {
                    leftCircuit.addAll(rightCircuit);
                    circuits.remove(rightCircuit);
                }
            } else if (leftCircuit != null) {
                leftCircuit.add(pair.getRight());
            } else if (rightCircuit != null) {
                rightCircuit.add(pair.getLeft());
            } else {
                HashSet<Integer> circuit = new HashSet<>();
                circuit.add(pair.getLeft());
                circuit.add(pair.getRight());
                circuits.add(circuit);
            }
        } while (circuits.getFirst().size() != input.size());
        return Long.parseLong(input.get(pair.getLeft()).split(",")[0]) * Long.parseLong(input.get(pair.getRight()).split(",")[0]);
    }

}
