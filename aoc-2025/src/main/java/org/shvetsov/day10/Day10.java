package org.shvetsov.day10;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2025/day/10">Day 10</a>
 */
public class Day10 {

    public long partOne(List<String> input) {
        int minButtonsPressed = 0;
        for (String manual : input) {
            int targetState = getTargetState(manual);
            List<Integer> buttons = getButtons(manual);
            minButtonsPressed += calculateMinButtons(buttons, targetState, 0);
        }
        return minButtonsPressed;
    }

    //TODO: add cache
    private int calculateMinButtons(List<Integer> buttons, int targetState, int currentState) {
        if (currentState == targetState) {
            return 0;
        } else if (buttons.isEmpty()) {
            return 1000;
        }
        int minButtonsPressed = Integer.MAX_VALUE;
        while (!buttons.isEmpty()) {
            int button = buttons.removeFirst();
            currentState ^= button;
            int min = calculateMinButtons(new ArrayList<>(buttons), targetState, currentState) + 1;
            minButtonsPressed = Math.min(minButtonsPressed, min);
            currentState ^= button;
        }
        return minButtonsPressed;
    }

    private int getTargetState(String manual) {
        int targetState = 0;
        char[] indicators = StringUtils.substringBetween(manual, "[", "]").toCharArray();
        for (int i = 0; i < indicators.length; i++) {
            if (indicators[i] == '#') {
                targetState |= 1 << i;
            }
        }
        return targetState;
    }

    private List<Integer> getButtons(String manual) {
        List<Integer> buttons = new ArrayList<>();
        String[] buttonsString = StringUtils.substringBetween(manual, "] ", " {").split(" ");
        for (String buttonString : buttonsString) {
            String[] impactedLights = buttonString.substring(1, buttonString.length() - 1).split(",");
            int button = 0;
            for (String impactedLight : impactedLights) {
                button |= 1 << Integer.parseInt(impactedLight);
            }
            buttons.add(button);
        }
        return buttons;
    }

    public long partTwo(List<String> input) {
        int minButtonsPressed = 0;
        for (String manual : input) {
            List<List<Integer>> buttons = new ArrayList<>();
            String[] buttonsString = StringUtils.substringBetween(manual, "] ", " {").split(" ");
            for (String buttonString : buttonsString) {
                buttons.add(
                        Arrays.stream(buttonString.substring(1, buttonString.length() - 1).split(","))
                                .map(Integer::parseInt)
                                .toList()
                );
            }
            String[] joltageString = StringUtils.substringBetween(manual, "{", "}").split(",");
            int[] joltage = Arrays.stream(joltageString)
                    .map(Integer::parseInt)
                    .mapToInt(value -> value)
                    .toArray();

            minButtonsPressed += calculateMinButtonsJoltage(buttons, joltage);
            cache.clear();
        }
        return minButtonsPressed;
    }

    private final Map<Key, Integer> cache = new HashMap<>();

    public int calculateMinButtonsJoltage(List<List<Integer>> buttons, int[] joltage) {
        Key key = new Key(joltage);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        boolean allZero = true;
        for (int i : joltage) {
            if (i < 0) {
                return 1000;
            } else if (i > 0) {
                allZero = false;
            }
        }
        if (allZero) {
            cache.put(key, 0);
            return 0;
        }
        int minButtonsPressed = Integer.MAX_VALUE;
        for (List<Integer> button : buttons) {
            for (Integer i : button) {
                joltage[i]--;
            }
            minButtonsPressed = Math.min(minButtonsPressed, calculateMinButtonsJoltage(buttons, joltage) + 1);
            for (Integer i : button) {
                joltage[i]++;
            }
        }
        cache.put(key, minButtonsPressed);
        return minButtonsPressed;
    }

    public record Key(int[] joltage) {
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Key(int[] joltage1))) return false;

            return Arrays.equals(joltage, joltage1);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(joltage);
        }
    }

}
