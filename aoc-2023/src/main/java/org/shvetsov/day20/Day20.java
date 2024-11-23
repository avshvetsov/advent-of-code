package org.shvetsov.day20;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.function.Predicate;

/**
 * <a href="https://adventofcode.com/2023/day/20">Day 20</a>
 */
public class Day20 {

    private static final String OUTPUT_NAME = "rx";
    private static final String BROADCASTER_NAME = "broadcaster";
    private static final String BUTTON_NAME = "button";

    public long partOneAnton(List<String> inputList) {
        long[] pulseCounts = new long[2];
        Map<String, Module> modules = instantiateMachine(inputList);

        Button button = new Button(BUTTON_NAME, List.of(BROADCASTER_NAME), modules);
        Queue<Triple<Module, Module, PulseType>> processQueue = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            processQueue.addAll(button.pulse(null, PulseType.LOW));
            while (!processQueue.isEmpty()) {
                Triple<Module, Module, PulseType> current = processQueue.poll();
                if (current.getRight() == PulseType.LOW) {
                    pulseCounts[0]++;
                } else pulseCounts[1]++;
                processQueue.addAll(current.getMiddle().pulse(current.getLeft(), current.getRight()));
            }
        }
        return pulseCounts[0] * pulseCounts[1];
    }

    private Map<String, Module> instantiateMachine(List<String> inputList) {
        //setup modules
        Map<String, Module> modules = new HashMap<>();
        for (String input : inputList) {
            switch (input) {
                case String s when s.startsWith(BROADCASTER_NAME) -> {
                    String name = extractModuleName(s);
                    List<String> receivers = extractReceivers(s);
                    modules.put(name, new Broadcast(name, receivers));
                }
                case String s when s.startsWith("%") -> {
                    String name = extractModuleName(s.substring(1));
                    List<String> receivers = extractReceivers(s);
                    modules.put(name, new FlipFlop(name, receivers));
                }
                case String s when s.startsWith("&") -> {
                    String name = extractModuleName(s.substring(1));
                    List<String> receivers = extractReceivers(s);
                    modules.put(name, new Conjunction(name, receivers));
                }
                default -> throw new IllegalStateException("Unexpected value: " + input);
            }
        }
        //populate modules
        modules.values().forEach(module -> module.populateReceivers(modules));
        //add outputs to map
        List<Module> outputs = modules.values().stream()
                .flatMap(module -> module.getReceivers().stream())
                .distinct()
                .filter(Predicate.not(module -> modules.containsKey(module.getName())))
                .toList();
        for (Module output : outputs) {
            modules.put(output.getName(), output);
        }
        return modules;
    }

    private String extractModuleName(String s) {
        return StringUtils.substringBefore(s, " ->");
    }

    private List<String> extractReceivers(String s) {
        String[] split = StringUtils.substringAfter(s, "-> ").split(", ");
        return Arrays.asList(split);
    }

    public long partTwoAnton(List<String> inputList) {
        Map<String, Module> modules = instantiateMachine(inputList);
        Button button = new Button(BUTTON_NAME, List.of(BROADCASTER_NAME), modules);

        Map<String, Integer> highPulseConjunctionInputCountPressButtonMap = new HashMap<>();
        Output output = (Output) modules.get(OUTPUT_NAME);
        List<Module> inputsForOutput = output.getInputs();
        if (inputsForOutput.size() > 1) throw new UnsupportedOperationException("Output '" + OUTPUT_NAME + "' has more than one input");
        Module singleInputForOutput = inputsForOutput.getFirst();
        if (singleInputForOutput instanceof Conjunction conjunction) {
            conjunction.getInputMap().keySet().forEach(s -> highPulseConjunctionInputCountPressButtonMap.put(s, -1));
        } else
            throw new UnsupportedOperationException("Type is not supported: " + singleInputForOutput.getClass() + ". Must implement other input type for output before run.");

        Queue<Triple<Module, Module, PulseType>> processQueue = new LinkedList<>();
        int i = 0;
        while (allHighPulseCounted(highPulseConjunctionInputCountPressButtonMap)) {
            i++;
            processQueue.addAll(button.pulse(null, PulseType.LOW));
            while (!processQueue.isEmpty()) {
                Triple<Module, Module, PulseType> current = processQueue.poll();
                if (current.getMiddle() instanceof Conjunction currentConjunction && currentConjunction.getName().equals(singleInputForOutput.getName())) {
                    if (currentConjunction.getInputMap().containsValue(PulseType.HIGH)) {
                        List<String> highInputs = currentConjunction.getInputMap().entrySet().stream()
                                .filter(entry -> entry.getValue() == PulseType.HIGH)
                                .map(Map.Entry::getKey)
                                .toList();
                        for (String highInput : highInputs) {
                            if (highPulseConjunctionInputCountPressButtonMap.get(highInput) == -1) {
                                highPulseConjunctionInputCountPressButtonMap.put(highInput, i);
                            }
                        }
                    }
                }
                processQueue.addAll(current.getMiddle().pulse(current.getLeft(), current.getRight()));
            }
        }
        return highPulseConjunctionInputCountPressButtonMap.values().stream().mapToLong(value -> (long) value).reduce(1L, (n, m) -> n * m);
    }

    private boolean allHighPulseCounted(Map<String, Integer> highPulseConjunctionInputCountPressButtonMap) {
        return highPulseConjunctionInputCountPressButtonMap.values().stream().anyMatch(integer -> integer < 0);
    }

}