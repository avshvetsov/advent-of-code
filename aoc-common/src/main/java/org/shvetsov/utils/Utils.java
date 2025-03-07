package org.shvetsov.utils;

import com.google.common.io.Resources;
import com.google.common.primitives.Chars;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static char[][] parseInputInTwoDimArray(String pathToFile) {
        List<String> lines = parseInputByNewLine(pathToFile);
        return parseInputInTwoDimArray(lines);
    }

    public static char[][] parseInputInTwoDimArray(List<String> inputLines) {
        int columns = inputLines.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        char[][] charsTwoDimArray = new char[inputLines.size()][columns];
        for (int i = 0; i < inputLines.size(); i++) {
            System.arraycopy(inputLines.get(i).toCharArray(), 0, charsTwoDimArray[i], 0, columns);
        }
        return charsTwoDimArray;
    }

    public static List<List<Character>> parseInputInCharacterGrid(List<String> inputLines) {
        List<List<Character>> list = new ArrayList<>();
        inputLines.forEach(line -> list.add(new ArrayList<>(Chars.asList(line.toCharArray()))));
        return list;
    }
    public static List<List<Integer>> parseInputInIntegerGrid(List<String> inputLines) {
        List<List<Integer>> list = new ArrayList<>();
        inputLines.forEach(line -> list.add(Chars.asList(line.toCharArray()).stream().map(Character::getNumericValue).collect(Collectors.toList())));
        return list;
    }

    public static boolean isIndexExistInArray(int[] array, int i) {
        return i >= 0 && i < array.length;
    }

    public static boolean isIndexExistInArray(char[][] array, int row, int column) {
        return isIndexExistInArrayBounds(array.length, array[0].length, row, column);
    }
    public static boolean isIndexExistInArray(int[][] array, int row, int column) {
        return isIndexExistInArrayBounds(array.length, array[0].length, row, column);
    }

    public static <T> boolean isIndexExistInArray(T[][] array, int row, int column) {
        return isIndexExistInArrayBounds(array.length, array[0].length, row, column);
    }

    private static boolean isIndexExistInArrayBounds(int m, int n, int r, int c) {
        if (r < 0 || r >= m) {
            return false;
        }
        if (c < 0 || c >= n) {
            return false;
        }
        return true;
    }

    @SneakyThrows
    public static List<String> parseInputByNewLine(String pathToFile) {
        Path path = Paths.get(pathToFile);
        return Files.readAllLines(path);
    }



    public static String[] parseInputDany(String pathToFile) {
        try {
            return Resources.toString(Resources.getResource(pathToFile), Charset.defaultCharset()).split("\r\n");
        } catch (IOException e) {
            System.out.println("Couldn't read file by path: " + pathToFile);
            return new String[]{};
        }
    }

    public static char[][] stringArrayToCharsDany(String[] input) {
        int height = input.length;
        int width = input[0].length();
        char[][] chars = new char[height][width];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                chars[i][j] = input[i].charAt(j);
            }
        }
        return chars;
    }
}
