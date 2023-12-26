package org.shvetsov.day13;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2023/day/13">Day 13</a>
 */
public class Day13 {

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> inputList) {
        int res = 0;
        List<String> pattern = new ArrayList<>();
        inputList.add("");
        for (String s : inputList) {
            if (s.isBlank()) {
                int row = lookupMirror(pattern);
                int column = 0;
                if (row == 0) {
                    List<String> transposedPattern = transpose(pattern);
                    column = lookupMirror(transposedPattern);
                }
                res = res + row * 100 + column;
//                pattern.forEach(System.out::println);
//                System.out.println("row = " + row + ", column = " + column);
                pattern.clear();
                continue;
            }
            pattern.add(s);
        }

        return res;
    }


    public int partTwoAnton(List<String> inputList) {
        int res = 0;
        List<String> pattern = new ArrayList<>();
        inputList.add("");
        for (String s : inputList) {
            if (s.isBlank()) {
                int row = lookupMirrorWithSmudges(pattern);
                int column = 0;
                if (row == 0) {
                    List<String> transposedPattern = transpose(pattern);
                    column = lookupMirrorWithSmudges(transposedPattern);
                }
                res = res + row * 100 + column;
//                pattern.forEach(System.out::println);
//                System.out.println("row = " + row + ", column = " + column);
                pattern.clear();
                continue;
            }
            pattern.add(s);
        }

        return res;
    }

    public int lookupMirror(List<String> pattern) {
        int res = 0;
        for (int i = 0, j = 1; j < pattern.size(); i++, j++) {
            if (pattern.get(i).equals(pattern.get(j))) {
                int row = i + 1;
                int b = i - 1;
                int f = j + 1;
                boolean isMirror = true;
                while (b >= 0 && f < pattern.size()) {
                    if (!pattern.get(b).equals(pattern.get(f))) {
                        isMirror = false;
                        break;
                    }
                    b--;
                    f++;
                }
                if (isMirror) {
                    res += row;
                }
            }
        }
        return res;
    }

    public int lookupMirrorWithSmudges(List<String> pattern) {
        int res = 0;
        for (int i = 0, j = 1; j < pattern.size(); i++, j++) {
            int smudges = 0;
            smudges += countSmudges(pattern.get(i), pattern.get(j));
            if (smudges <= 1) {
                int row = i + 1;
                int b = i - 1;
                int f = j + 1;
                boolean isMirror = true;
                while (b >= 0 && f < pattern.size()) {
                    smudges += countSmudges(pattern.get(b), pattern.get(f));
                    if (smudges > 1) {
                        isMirror = false;
                        break;
                    }
                    b--;
                    f++;
                }
                if (isMirror && smudges == 1) {
                    res += row;
                }
            }
        }
        return res;
    }

    private int countSmudges(String s1, String s2) {
        int smudges = 0;
        if (s1.equals(s2)) {
            return smudges;
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    smudges++;
                }
            }
            return smudges;
        }
    }

    private List<String> transpose(List<String> pattern) {
        List<String> transposedPattern = new ArrayList<>();
        for (int i = 0; i < pattern.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder(pattern.size());
            for (String s : pattern) {
                sb.append(s.charAt(i));
            }
            transposedPattern.add(sb.toString());
        }
        return transposedPattern;
    }

}