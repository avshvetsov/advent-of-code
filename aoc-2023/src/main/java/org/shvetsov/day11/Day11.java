package org.shvetsov.day11;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.shvetsov.utils.Grid;
import org.shvetsov.utils.PointOld;
import org.shvetsov.utils.Utils;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/11">Day 11</a>
 */
public class Day11 {


    public static void main(String[] args) {

    }

    public long partOneAnton(List<String> inputList) {
        Universe universe = Universe.ofCharacter(inputList);
        universe.expand();
        List<PointOld> list = EntryStream.of(universe.getPointMap())
                .filter(entry -> entry.getValue() == '#')
                .map(Map.Entry::getKey)
                .toList();

        return StreamEx.ofPairs(list, PointOld::distance)
                .mapToInt(value -> value)
                .sum();

    }


    public BigInteger partTwoAnton(List<String> inputList, Integer multiplier) {
        Universe universe = Universe.ofCharacter(inputList);
        return universe.galaxiesDistance(multiplier);
    }

    public static class Universe extends Grid<Character> {

        private final Set<Integer> emptyRow = new HashSet<>();
        private final Set<Integer> emptyColumn = new HashSet<>();

        private Universe(List<List<Character>> lists) {
            super(lists);
            findEmptyRows();
            findEmptyColumns();
        }

        public static Universe ofCharacter(List<String> lines) {
            return new Universe(Utils.parseInputInCharacterGrid(lines));
        }

        private void findEmptyRows() {
            IntStream.range(0, sizeRow())
                    .filter(i -> getRow(i).stream().allMatch(character -> character == '.'))
                    .forEach(emptyRow::add);
        }

        private void findEmptyColumns() {
            IntStream.range(0, sizeColumn())
                    .filter(i -> getColumn(i).stream().allMatch(character -> character == '.'))
                    .forEach(emptyColumn::add);
        }

        private BigInteger galaxiesDistance(Integer multiplier) {
            Integer additionalRowMultiplier = multiplier - 1;
            List<PointOld> galaxies = EntryStream.of(getPointMap())
                    .filter(entry -> entry.getValue() == '#')
                    .map(Map.Entry::getKey)
                    .toList();
            return StreamEx.ofPairs(galaxies, (point, point2) -> {
                        int simpleDistance = point.distance(point2);
                        long countEmptyRows = IntStream.range(Math.min(point.r, point2.r), Math.max(point.r, point2.r))
                                .filter(emptyRow::contains)
                                .count();
                        long countEmptyColumns = IntStream.range(Math.min(point.c, point2.c), Math.max(point.c, point2.c))
                                .filter(emptyColumn::contains)
                                .count();
                        return BigInteger.valueOf(simpleDistance + (countEmptyRows + countEmptyColumns) * additionalRowMultiplier);
                    })
                    .reduce(BigInteger.ZERO, BigInteger::add);
        }

        private void expand() {
            for (int i = 0; i < sizeRow(); i++) {
                boolean emptyRow = true;
                for (int j = 0; j < sizeColumn(); j++) {
                    if (getValue(PointOld.of(i, j)) != '.') {
                        emptyRow = false;
                        break;
                    }
                }
                if (emptyRow) {
                    insertRow(i, getRow(i));
                    i++;
                }
            }
            for (int i = 0; i < sizeColumn(); i++) {
                boolean emptyColumn = true;
                for (int j = 0; j < sizeRow(); j++) {
                    if (getValue(PointOld.of(j, i)) != '.') {
                        emptyColumn = false;
                        break;
                    }
                }
                if (emptyColumn) {
                    insertColumn(i, getColumn(i));
                    i++;
                }
            }
        }

    }


}