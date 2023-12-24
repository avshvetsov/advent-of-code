package org.shvetsov.utils;

import com.google.common.primitives.Chars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public T getValue(Point point) {
        return this.elements.get(point.r).get(point.c);
    }

    public void setValue(Point point, T value) {
        this.elements.get(point.r).set(point.c, value);
    }

    public Optional<Point> getPoint(Point point) {
        return  (point.c >= 0 && point.c < sizeColumn() && point.r >= 0 && point.r < sizeRow()) ? Optional.of(point) : Optional.empty();
    }

    public boolean containsPoint(Point point) {
        return getPoint(point).isPresent();
    }

    public Map<Point, T> getPointMap() {
        return getAllPoints().stream().collect(Collectors.toMap(Function.identity(), this::getValue));
    }

    public List<Point> getAllPoints() {
        return IntStream.range(0, sizeRow())
                .mapToObj(row -> IntStream.range(0, sizeColumn()).mapToObj(col -> Point.of(row, col)).collect(Collectors.toList())).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void draw(Function<T, String> toStringFunction) {
        for (int i = 0; i < sizeRow(); i++) {
            List<String> strings = elements.get(i).stream().map(toStringFunction).toList();
            System.out.println(String.join("", strings));
        }
    }

}
