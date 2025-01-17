package org.shvetsov.day23;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2024/day/23">Day 23</a>
 */
public class Day23 {

    public long partOne(List<String> input) {
        String historian = "t";
        Map<String, List<String>> connections = new HashMap<>();
        for (String connection : input) {
            String[] comps = connection.split("-");
            connections.computeIfAbsent(comps[0], k -> new ArrayList<>()).add(comps[1]);
            connections.computeIfAbsent(comps[1], k -> new ArrayList<>()).add(comps[0]);
        }
        Set<Set<String>> groups = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : connections.entrySet()) {
            String first = entry.getKey();
            List<String> firstConn = entry.getValue();
            for (String second : firstConn) {
                List<String> secondConn = connections.get(second);
                List<String> common = new ArrayList<>(firstConn);
                common.retainAll(secondConn);
                for (String third : common) {
                    if (first.startsWith(historian) || second.startsWith(historian) || third.startsWith(historian)) {
                        Set<String> groupOfThree = new HashSet<>();
                        groupOfThree.add(first);
                        groupOfThree.add(second);
                        groupOfThree.add(third);
                        groups.add(groupOfThree);
                    }
                }
            }
        }
        return groups.size();
    }

    public String partTwo(List<String> input) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String connection : input) {
            String[] comps = connection.split("-");
            graph.computeIfAbsent(comps[0], k -> new HashSet<>()).add(comps[1]);
            graph.computeIfAbsent(comps[1], k -> new HashSet<>()).add(comps[0]);
        }
        Set<Set<String>> allCliques = bronKerbosch(graph, false, new HashSet<>(), graph.keySet(), new HashSet<>());
        Set<String> maxClique = allCliques.stream()
                .max(Comparator.comparingInt(Set::size))
                .orElseThrow(() -> new RuntimeException("No clique found"));
        return maxClique.stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    public Set<Set<String>> bronKerbosch(Map<String, Set<String>> graph, boolean addSubCliques, Set<String> R, Set<String> P, Set<String> X) {
        Set<Set<String>> cliques = new HashSet<>();
        //якщо прибрати умову P.isEmpty() && X.isEmpty(), то в результат будуть додані усі клійки, які є саб-клійками великих
        if (addSubCliques || (P.isEmpty() && X.isEmpty())) {
            cliques.add(R);
        }
        while (!P.isEmpty()) {
            String currV = P.iterator().next();
            Set<String> neighborV = graph.get(currV);
            Set<String> newR = new HashSet<>(R);
            newR.add(currV);
            Set<String> newP = new HashSet<>(P);
            newP.retainAll(neighborV);
            Set<String> newX = new HashSet<>(X);
            newX.retainAll(neighborV);
            cliques.addAll(bronKerbosch(graph, addSubCliques, newR, newP, newX));
            P.remove(currV);
            X.add(currV);
        }
        return cliques;
    }


    public long partOneBronKerbosch(List<String> input) {
        String historian = "t";
        Map<String, Set<String>> graph = new HashMap<>();
        for (String connection : input) {
            String[] comps = connection.split("-");
            graph.computeIfAbsent(comps[0], k -> new HashSet<>()).add(comps[1]);
            graph.computeIfAbsent(comps[1], k -> new HashSet<>()).add(comps[0]);
        }
        Set<Set<String>> allCliques = bronKerboschWithCliqueSize(graph, true, 3, 4, new HashSet<>(), graph.keySet(), new HashSet<>());
        return allCliques.stream()
                .filter(clique -> clique.stream().anyMatch(comp -> comp.startsWith(historian)))
                .count();
    }

    public Set<Set<String>> bronKerboschWithCliqueSize(Map<String, Set<String>> graph, boolean addSubCliques, int cliqueSizeFrom, int cliqueSizeTo, Set<String> R, Set<String> P, Set<String> X) {
        Set<Set<String>> cliques = new HashSet<>();
        if ((addSubCliques || (P.isEmpty() && X.isEmpty())) &&
            R.size() >= cliqueSizeFrom && R.size() < cliqueSizeTo) {
            cliques.add(R);
        }
        while (!P.isEmpty()) {
            String currV = P.iterator().next();
            Set<String> neighborV = graph.get(currV);
            Set<String> newR = new HashSet<>(R);
            newR.add(currV);
            Set<String> newP = new HashSet<>(P);
            newP.retainAll(neighborV);
            Set<String> newX = new HashSet<>(X);
            newX.retainAll(neighborV);
            cliques.addAll(bronKerboschWithCliqueSize(graph, addSubCliques, cliqueSizeFrom, cliqueSizeTo, newR, newP, newX));
            P.remove(currV);
            X.add(currV);
        }
        return cliques;
    }

}