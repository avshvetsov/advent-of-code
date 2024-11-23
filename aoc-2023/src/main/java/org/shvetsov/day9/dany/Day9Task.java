package org.shvetsov.day9.dany;


import org.shvetsov.utils.Utils;

public class Day9Task {

    public static void main(String[] args) {

        String[] input = Utils.parseInputDany("day9_data.txt");

        int result1 = part1(input);
        int result2 = part2(input);

        System.out.println("Result for part1: " + result1);
        System.out.println("Result for part2: " + result2);

    }

    public static int part1(String[] input) {
        int result = 0;
        for (String s : input) {
            ValuesHistory valuesHistory = new ValuesHistory(s);
            result += valuesHistory.predictNextValue();
        }
        return result;
    }

    public static int part2(String[] input) {
        int result = 0;
        for (String s : input) {
            ValuesHistory valuesHistory = new ValuesHistory(s);
            result += valuesHistory.predictPreviousValue();
        }
        return result;
    }

}