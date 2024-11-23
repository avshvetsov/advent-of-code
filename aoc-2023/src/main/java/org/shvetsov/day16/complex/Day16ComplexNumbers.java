package org.shvetsov.day16.complex;

import org.apache.commons.math3.complex.Complex;

import java.util.*;

import static org.shvetsov.utils.Direction.EAST;
import static org.shvetsov.utils.Direction.SOUTH;

/**
 * <a href="https://adventofcode.com/2023/day/16">Day 16</a>
 */
public class Day16ComplexNumbers {

    public static void main(String[] args) {

    }

    public long partOneAnton(List<String> inputList) {
        Contraption contraption = new Contraption(inputList);
        return contraption.traceLight(new PosDir(new Complex(-1, 0), EAST.toComplex()));
    }

    public long partTwoAnton(List<String> inputList) {
        Contraption contraption = new Contraption(inputList);
        long res = 0L;
        int size = inputList.size();
        PosDir start = new PosDir(new Complex(-1, 1), EAST.toComplex());
        Complex direction = SOUTH.toComplex();
        PosDir current = new PosDir(start.pos().add(direction), EAST.toComplex());
        while (!start.pos().equals(current.pos())) {
            if (current.pos().getReal() <= size && current.pos().getReal() >= -1 && current.pos().getImaginary() >= -size && current.pos().getImaginary() <= 1) {
                long energized = contraption.traceLight(current);
                res = Math.max(res, energized);
            } else {
                current = new PosDir(current.pos().add(direction.negate()), current.dir().multiply(Complex.I));
                direction = direction.multiply(Complex.I);
            }

            current = new PosDir(current.pos().add(direction), current.dir());
        }
        return res;
    }

    public static class Contraption {
        public Map<Complex, Character> device = new HashMap<>();

        public Contraption(List<String> lines) {
            int r = 0, i = 0;
            for (String line : lines) {
                for (char c : line.toCharArray()) {
                    device.put(new Complex(r, i), c);
                    r++;
                }
                r = 0;
                i--;
            }
        }

        public long traceLight(PosDir input) {
            Deque<PosDir> todo = new LinkedList<>();
            todo.add(input);
            Set<PosDir> done = new HashSet<>();
            while (!todo.isEmpty()) {
                PosDir elem = todo.pop();
                if (!done.contains(elem)) {
                    done.add(elem);
                    Complex dir = elem.dir();
                    Complex pos = elem.pos().add(dir);
                    switch (device.get(pos)) {
                        case '|' -> {
                            if (dir.getReal() != 0) {
                                todo.add(new PosDir(pos, dir.multiply(Complex.I)));
                                todo.add(new PosDir(pos, dir.multiply(Complex.I).negate()));
                            } else {
                                todo.add(new PosDir(pos, dir));
                            }
                        }
                        case '-' -> {
                            if (dir.getImaginary() != 0) {
                                todo.add(new PosDir(pos, dir.multiply(Complex.I)));
                                todo.add(new PosDir(pos, dir.multiply(Complex.I).negate()));
                            } else {
                                todo.add(new PosDir(pos, dir));
                            }
                        }
                        case '/' -> todo.add(new PosDir(pos, dir.conjugate().multiply(Complex.I)));
                        case '\\' -> todo.add(new PosDir(pos, dir.conjugate().multiply(Complex.I).negate()));
                        case '.' -> todo.add(new PosDir(pos, dir));
                        case null, default -> {

                        }
                    }
                }
            }
            return done.stream().map(PosDir::pos).distinct().count() - 1;
        }
    }

    public record PosDir(Complex pos, Complex dir) {
    }

}