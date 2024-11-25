package org.shvetsov.day7;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://adventofcode.com/2022/day/7">Day 7</a>
 */
public class Day7 {

    public long partOne(List<String> input) {
        int MAX_SIZE = 100_000;
        Map<String, SystemItem> fileSystem = buildFileSystem(input);
        return fileSystem.values().stream()
                .filter(systemItem -> systemItem instanceof Directory)
                .mapToLong(SystemItem::getSize)
                .filter(size -> size <= MAX_SIZE)
                .sum();
    }

    private Map<String, SystemItem> buildFileSystem(List<String> input) {
        Map<String, SystemItem> itemMap = new HashMap<>();
        StringBuilder currentPath = new StringBuilder();
        currentPath.append("/");
        Directory currentDir = new Directory(currentPath.toString());
        itemMap.put(currentPath.toString(), currentDir);
        for (int i = 1; i < input.size(); i++) {
            String s = input.get(i);
            if (s.startsWith("$ ls")) {
                continue;
            } else if (s.startsWith("$ cd ..")) {
                removeLastSegment(currentPath);
            } else if (s.startsWith("$ cd")) {
                String dirName = StringUtils.substringAfter(s, "$ cd ");
                currentPath.append(dirName).append('/');
                if (itemMap.computeIfAbsent(currentPath.toString(), Directory::new) instanceof Directory dir) {
                    currentDir = dir;
                } else throw new IllegalArgumentException("Can't '$ cd ' into non-directory");
            } else if (s.startsWith("dir")) {
                String dir = StringUtils.substringAfter(s, "dir ");
                SystemItem item = itemMap.computeIfAbsent(currentPath + dir + '/', Directory::new);
                currentDir.getChildren().add(item);
            } else if (Character.isDigit(s.charAt(0))) {
                String[] sizeName = s.split(" ");
                SystemItem item = itemMap.computeIfAbsent(currentPath + sizeName[1], k -> new File(k, Long.parseLong(sizeName[0])));
                currentDir.getChildren().add(item);
            }
        }
        itemMap.get("/").calculateSize();
        return itemMap;
    }

    private void removeLastSegment(StringBuilder currentPath) {
        do {
            currentPath.deleteCharAt(currentPath.length() - 1);
        } while (!currentPath.isEmpty() && currentPath.charAt(currentPath.length() - 1) != '/');
    }


    public long partTwo(List<String> input) {
        long TOTAL = 70_000_000;
        long UPDATE = 30_000_000;
        Map<String, SystemItem> fileSystem = buildFileSystem(input);
        long FREE = TOTAL - fileSystem.get("/").getSize();
        long NEEDED = UPDATE - FREE;
        return fileSystem.values().stream()
                .filter(systemItem -> systemItem instanceof Directory)
                .mapToLong(SystemItem::getSize)
                .filter(size -> size >= NEEDED)
                .sorted()
                .findAny().orElseThrow();
    }

}