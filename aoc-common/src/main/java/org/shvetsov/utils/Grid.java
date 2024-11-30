package org.shvetsov.utils;

import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EqualsAndHashCode
public class Grid<T> {

    protected final List<List<T>> elements;

    protected Grid(List<List<T>> elements) {
        this.elements = elements;
    }

    protected Grid() {
        this.elements = new ArrayList<>();
    }

    public static <T> Grid<T> of(List<List<T>> elements) {
        return new Grid<>(elements);
    }

    public static Grid<Character> ofCharacter(List<String> lines) {
        return new Grid<>(Utils.parseInputInCharacterGrid(lines));
    }

    public List<List<T>> getElementsCopy() {
        return elements.stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

    public void addRow(List<T> row) {
        this.elements.add(row);
    }

    public void insertRow(int index, List<T> row) {
        this.elements.add(index, row);
    }

    public List<T> getRow(int index) {
        return new ArrayList<>(elements.get(index));
    }

    public void addColumn(List<T> col) {
        assert col.size() == elements.size();
        for (int r = 0; r < col.size(); r++) {
            List<T> row = elements.get(r);
            row.add(col.get(r));
        }
    }

    public void insertColumn(int index, List<T> col) {
        assert col.size() == elements.size();
        for (int r = 0; r < col.size(); r++) {
            List<T> row = elements.get(r);
            row.add(index, col.get(r));
        }
    }

    public List<T> getColumn(int index) {
        return elements.stream().map(row -> row.get(index)).collect(Collectors.toList());
    }

    public int sizeRow() {
        return elements.size();
    }

    public int sizeColumn() {
        return elements.get(0).size();
    }
    public int sizeColumn(int index) {
        return elements.get(index).size();
    }

    public T getValue(PointOld point) {
        return this.elements.get(point.r).get(point.c);
    }

    public T getValue(int row, int col) {
        return this.elements.get(row).get(col);
    }

    public void setValue(PointOld point, T value) {
        this.elements.get(point.r).set(point.c, value);
    }

    public void setValue(int row, int col, T value) {
        this.elements.get(row).set(col, value);
    }

    public Optional<PointOld> getPoint(PointOld point) {
        return  (point.c >= 0 && point.c < sizeColumn() && point.r >= 0 && point.r < sizeRow()) ? Optional.of(point) : Optional.empty();
    }

    public boolean isContainPoint(PointOld point) {
        return getPoint(point).isPresent();
    }

    public Map<PointOld, T> getPointMap() {
        return getAllPoints().stream().collect(Collectors.toMap(Function.identity(), this::getValue));
    }

    public List<PointOld> getAllPoints() {
        return IntStream.range(0, sizeRow())
                .mapToObj(row -> IntStream.range(0, sizeColumn()).mapToObj(col -> PointOld.of(row, col)).collect(Collectors.toList())).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void swap(PointOld point1, PointOld point2) {
        T temp = getValue(point1);
        setValue(point1, getValue(point2));
        setValue(point2, temp);
    }

    public List<T> getNeighbourValues(PointOld point) {
        return EnumSet.allOf(Direction.class).stream()
                .map(point::move)
                .filter(this::isContainPoint)
                .map(this::getValue)
                .collect(Collectors.toList());
    }

    public List<PointOld> getNeighbourPoints(PointOld point) {
        return EnumSet.allOf(Direction.class).stream()
                .map(point::move)
                .filter(this::isContainPoint)
                .collect(Collectors.toList());
    }

    public List<Direction> getNeighbourDirections(PointOld point) {
        return EnumSet.allOf(Direction.class).stream()
                .filter(direction -> isContainPoint(point.move(direction)))
                .collect(Collectors.toList());
    }

    public void draw(Function<T, String> toStringFunction) {
        for (int i = 0; i < sizeRow(); i++) {
            List<String> strings = elements.get(i).stream().map(toStringFunction).toList();
            System.out.println(String.join("", strings));
        }
    }

}
