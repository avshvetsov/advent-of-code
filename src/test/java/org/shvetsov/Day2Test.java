package org.shvetsov;

import org.junit.jupiter.api.Test;
import org.shvetsov.day2.Day2;
import org.shvetsov.day2.Day2Dany;
import org.shvetsov.day2.Game;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class Day2Test {

    private static final String EXAMPLE_PATH = "src/main/resources/example 2-1, 2-2.txt";
    private static final Integer EXAMPLE_DAY_2_PART_2_CORRECT = 2286;

    private static final Integer DAY_2_PART_1_CORRECT = 2101;
    private static final Integer DAY_2_PART_2_CORRECT = 58269;
    private final Day2 day2 = new Day2();

    @Test
    public void parseGameNumber() {
        List<String> gameStrings = Utils.parseInputByNewLine(EXAMPLE_PATH);
        List<Integer> gameNumbers = gameStrings.stream().map(Game::new).map(Game::getGameNumber).toList();
        assertThat(gameNumbers).hasSameElementsAs(List.of(1,2,3,4,5));
    }

    @Test
    public void parseReveals() {
        List<Game> games = Utils.parseInputByNewLine(EXAMPLE_PATH).stream()
                .map(Game::new).toList();

        assertThat(games.get(3).getReveals())
                .hasSameElementsAs(List.of(new Game.Reveal(3, 1, 6), new Game.Reveal(6, 3, 0), new Game.Reveal(14, 3, 15)));
    }

    @Test
    public void gamePowerTest() {
        int power = Utils.parseInputByNewLine(EXAMPLE_PATH).stream()
                .map(Game::new)
                .mapToInt(Game::getPower)
                .sum();

        assertThat(power).isEqualTo(EXAMPLE_DAY_2_PART_2_CORRECT);
    }

    @Test
    public void day2Part1Anton() {
        assertThat(day2.partOneAnton(Utils.parseInputByNewLine(Day2.INPUT_PATH))).isEqualTo(DAY_2_PART_1_CORRECT);
    }
    @Test
    public void day2Part2Anton() {
        assertThat(day2.partTwoAnton(Utils.parseInputByNewLine(Day2.INPUT_PATH))).isEqualTo(DAY_2_PART_2_CORRECT);
    }

    @Test
    public void day2Part1Dany() {
        assertThat(Day2Dany.part1Dany(Utils.parseInputDany(Day2Dany.INPUT))).isEqualTo(DAY_2_PART_1_CORRECT);
    }
    @Test
    public void day2Part2Dany() {
        assertThat(Day2Dany.part2Dany(Utils.parseInputDany(Day2Dany.INPUT))).isEqualTo(DAY_2_PART_2_CORRECT);
    }

}