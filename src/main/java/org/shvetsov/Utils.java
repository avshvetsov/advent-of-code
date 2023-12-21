package org.shvetsov;

import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    public static boolean isIndexExistInArray(char[][] array, int row, int column) {
        if (row < 0 || row >= array.length) {
            return false;
        }
        if (column < 0 || column >= array[row].length) {
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
}
