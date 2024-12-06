package org.shvetsov.day16;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2022/day/16">Day 16</a>
 */
public class Day16 {

    public long partOne(List<String> input) {
        //build graph
        Map<String, Integer> valvePressure = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        List<String> nonZeroValve = new ArrayList<>();
        nonZeroValve.add("AA");
        buildGraph(input, valvePressure, graph, nonZeroValve);

        //calculate min distances between pairs of not 0 valve
        Map<String, Set<Pair<String, Integer>>> valveDistances = calculateDistances(nonZeroValve, graph);
        return calculateMaxFlow(valvePressure, valveDistances, "AA", 30, new HashSet<>());
    }

    private void buildGraph(List<String> input, Map<String, Integer> valvePressure, Map<String, List<String>> graph, List<String> notZeroValves) {
        for (String s : input) {
            String name = StringUtils.substringBetween(s, "Valve ", " has");
            int pressure = Integer.parseInt(StringUtils.substringBetween(s, "rate=", ";"));
            String listString = StringUtils.substringAfter(s, "tunnels lead to valves ").isEmpty()
                    ? StringUtils.substringAfter(s, "tunnel leads to valve ")
                    : StringUtils.substringAfter(s, "tunnels lead to valves ");
            List<String> list = Arrays.stream(listString.split(", ")).toList();
            valvePressure.put(name, pressure);
            graph.put(name, list);
            if (pressure > 0) notZeroValves.add(name);
        }
    }

    private Map<String, Set<Pair<String, Integer>>> calculateDistances(List<String> valves, Map<String, List<String>> graph) {
        Map<String, Set<Pair<String, Integer>>> valveDistances = new HashMap<>();
        for (int i = 0; i < valves.size(); i++) {
            for (int j = i + 1; j < valves.size(); j++) {
                Integer distance = dijkstra(graph, valves.get(i), valves.get(j));
                valveDistances.computeIfAbsent(valves.get(i), s -> new HashSet<>()).add(Pair.of(valves.get(j), distance));
                valveDistances.computeIfAbsent(valves.get(j), s -> new HashSet<>()).add(Pair.of(valves.get(i), distance));
            }
        }
        return valveDistances;
    }

    private Integer dijkstra(Map<String, List<String>> graph, String start, String end) {
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getRight));
        pq.offer(Pair.of(start, 0));
        Set<String> visited = new HashSet<>();
        visited.add(start);
        while (!pq.isEmpty()) {
            Pair<String, Integer> current = pq.poll();
            if (current.getLeft().equals(end)) {
                return current.getRight();
            }
            for (String valve : graph.get(current.getLeft())) {
                if (!visited.contains(valve)) {
                    visited.add(valve);
                    pq.offer(Pair.of(valve, current.getRight() + 1));
                }
            }
        }
        throw new IllegalArgumentException("There is no way from start to end");
    }

    //пробовал добавлять memorization по new State(valve, timeLeft, visited)
    //производительность падает из-за того что на каждом витке рекурсии приходится создавать новый visited сет
    private long calculateMaxFlow(Map<String, Integer> valvePressure, Map<String, Set<Pair<String, Integer>>> valveDistances, String valve, int timeLeft, Collection<String> visited) {
        int pressure = valvePressure.get(valve);
        long currentFlow = 0;
        if (pressure > 0) {
            timeLeft--;
            currentFlow = (long) timeLeft * pressure;
        }
        long maxNextFlow = 0;
        visited.add(valve);
        for (Pair<String, Integer> next : valveDistances.get(valve)) {
            if (!visited.contains(next.getLeft()) && (timeLeft - next.getRight()) > 1) {
                maxNextFlow = Math.max(maxNextFlow, calculateMaxFlow(valvePressure, valveDistances, next.getLeft(), timeLeft - next.getRight(), visited));
            }
        }
        visited.remove(valve);
        return maxNextFlow + currentFlow;
    }


    public long partTwo(List<String> input) {
        //build graph
        Map<String, Integer> valvePressure = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        List<String> nonZeroValve = new ArrayList<>();
        buildGraph(input, valvePressure, graph, nonZeroValve);
        if (!nonZeroValve.contains("AA")) nonZeroValve.add("AA");
        //calculate min distances between pairs of non-zero valve
        Map<String, Set<Pair<String, Integer>>> valveDistances = calculateDistances(nonZeroValve, graph);
        //check each subset pair of non-zero valves
        long max = 0;
        nonZeroValve.remove("AA");
        for (int i = 0; i < (1 << (nonZeroValve.size() - 1)); i++) {
            Set<String> visitedMe = new HashSet<>();
            Set<String> visitedElephant = new HashSet<>();
            for (int j = 0; j < nonZeroValve.size(); j++) {
                if ((i & (1 << j)) == 0) {
                    visitedMe.add(nonZeroValve.get(j));
                } else {
                    visitedElephant.add(nonZeroValve.get(j));
                }
            }
            long me = calculateMaxFlow(valvePressure, valveDistances, "AA", 26, visitedMe);
            long elephant = calculateMaxFlow(valvePressure, valveDistances, "AA", 26, visitedElephant);
            max = Math.max(max, me + elephant);
        }
        return max;
    }




    private record State(String valve, int timeLeft, Set<String> visited) {

    }

    private Pair<Long, List<String>> calculateMaxFlowWithPath(Map<State, Pair<Long, List<String>>> dp, Map<String, Integer> valvePressure, Map<String, Set<Pair<String, Integer>>> valveDistances, String valve, int timeLeft, Set<String> visited) {
        State state = new State(valve, timeLeft, new HashSet<>(visited));
        if (dp.containsKey(state)) {
            return dp.get(state);
        }
        int pressure = valvePressure.get(valve);
        long flow = 0;
        if (pressure > 0) {
            timeLeft--;
            flow = (long) timeLeft * pressure;
        }
        long maxFlow = 0;
        List<String> path = new LinkedList<>();
        visited.add(valve);
        for (Pair<String, Integer> next : valveDistances.get(valve)) {
            if (!visited.contains(next.getLeft()) && (timeLeft - next.getRight()) > 1) {
                Pair<Long, List<String>> potentialMax = calculateMaxFlowWithPath(dp, valvePressure, valveDistances, next.getLeft(), timeLeft - next.getRight(), visited);
                if (potentialMax.getLeft() > maxFlow) {
                    maxFlow = potentialMax.getLeft();
                    path = new LinkedList<>(potentialMax.getRight());
                }
            }
        }
        visited.remove(valve);
        path.addFirst(valve);
        Pair<Long, List<String>> result = Pair.of(maxFlow + flow, path);
        dp.put(state, result);
        return result;
    }

}