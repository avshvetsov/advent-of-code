package org.shvetsov;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Utils {

    @SneakyThrows
    public static List<String> parseInputByNewLine(String pathToFile) {
        Path path = Paths.get(pathToFile);
        return Files.readAllLines(path);
    }
}
