package org.shvetsov.day2;

import org.shvetsov.Utils;

import java.util.List;

/**
 *<a href="https://adventofcode.com/2023/day/2">Day 2</a>
 */
public class Day2 {

    public static final int MAX_RED = 12;
    public static final int MAX_GREEN = 13;
    public static final int MAX_BLUE = 14;

    public static final String INPUT_PATH = "src/main/resources/input 2-1, 2-2.txt";

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine(INPUT_PATH);
        Day2 day = new Day2();
        System.out.println(day.partOneAnton(input));
        System.out.println(day.partTwoAnton(input));
    }

    public int partOneAnton(List<String> gameStrings) {
        return gameStrings.stream()
                .map(Game::new)
                .filter(game -> game.isValid(MAX_RED, MAX_GREEN, MAX_BLUE))
                .map(Game::getGameNumber)
                .mapToInt(i -> i)
                .sum();

    }

    public int partTwoAnton(List<String> gameStrings) {
        return gameStrings.stream()
                .map(Game::new)
                .mapToInt(Game::getPower)
                .sum();
    }

}
