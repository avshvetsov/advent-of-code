package org.shvetsov.day25;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://adventofcode.com/2023/day/25">Day 25</a>
 */
public class Day25 {

    public long partOneAnton(List<String> inputList, int monteCarloSize) {
        //build graph
        Map<String, List<String>> graph = buildGraph(inputList);
        List<String> keys = graph.keySet().stream().toList();
        int size = graph.size();

        Map<String, Integer> edgeCounts = new HashMap<>();
        for (int i = 0; i < monteCarloSize; i++) {
            String from = keys.get(ThreadLocalRandom.current().nextInt(size - 1));
            String to = keys.get(ThreadLocalRandom.current().nextInt(size - 1));
            List<String> path = dijkstra(graph, from, to);
            if (path == null) {
                throw new IllegalArgumentException("No dijkstra path found for " + from + " and " + to);
            }
            for (int j = 0; j < path.size() - 1; j++) {
                String e1 = path.get(j);
                String e2 = path.get(j + 1);
                String e = e1.compareTo(e2) < 0 ? e1 + " " + e2 : e2 + " " + e1;
                edgeCounts.merge(e, 1, Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> max = edgeCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .toList();

        for (Map.Entry<String, Integer> entry : max) {
            String[] v = entry.getKey().split(" ");
            graph.get(v[0]).remove(v[1]);
            graph.get(v[1]).remove(v[0]);
        }

        int size1 = graphSize(graph, keys.get(ThreadLocalRandom.current().nextInt(size - 1)));
        if (size1 == keys.size()) {
            return partOneAnton(inputList, monteCarloSize);
        }
        return (long) size1 * (keys.size() - size1);
    }

    private int graphSize(Map<String, List<String>> graph, String from) {
        Set<String> visited = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add(from);
        while (!current.isEmpty()) {
            Set<String> next = new HashSet<>();
            for (String v : current) {
                visited.add(v);
                List<String> childrens = graph.get(v);
                for (String child : childrens) {
                    if (!visited.contains(child)) {
                        next.add(child);
                    }
                }
            }
            current = next;
        }
        return visited.size();
    }

    private List<String> dijkstra(Map<String, List<String>> graph, String from, String to) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<List<String>> pq = new PriorityQueue<>(Comparator.comparingInt(List::size));
        List<String> fromList = new LinkedList<>();
        fromList.add(from);
        pq.offer(fromList);
        while (!pq.isEmpty()) {
            List<String> path = pq.poll();
            String current = path.getLast();
            if (current.equals(to)) {
                return path;
            }
            visited.add(current);
            List<String> nexts = graph.get(current);
            for (String next : nexts) {
                if (!visited.contains(next)) {
                    List<String> nextPath = new LinkedList<>(path);
                    nextPath.add(next);
                    pq.offer(nextPath);
                }
            }
        }
        return null;
    }


    private Map<String, List<String>> buildGraph(List<String> inputList) {
        Map<String, List<String>> graph = new HashMap<>();
        for (String line : inputList) {
            String[] split = line.split(": ");
            String[] childs = split[1].split(" ");
            for (String child : childs) {
                child = child.trim();
                graph.computeIfAbsent(split[0], k -> new ArrayList<>()).add(child);
                graph.computeIfAbsent(child, k -> new ArrayList<>()).add(split[0]);
            }
        }

        return graph;
    }

}