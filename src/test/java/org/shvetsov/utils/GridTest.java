package org.shvetsov.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GridTest {

    @Test
    public void drawStrings() {
        Grid<Character> characterGrid = Grid.ofCharacter(List.of("qwerty", "asdfgh", "zxcvbn", "......"));
        characterGrid.draw(String::valueOf);
    }

    @Test
    public void drawInts() {
        Grid<Integer> characterGrid = Grid.of(List.of(List.of(1,2,3), List.of(4,5,6), List.of(7,8,9)));
        characterGrid.draw(String::valueOf);
    }

    @Test
    public void addColumnShouldThrowException() {
        Grid<Integer> grid = Grid.of(List.of(List.of(1,2,3), List.of(4,5,6), List.of(7,8,9)));
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> grid.addColumn(List.of(1, 2, 3, 4)));
    }

}