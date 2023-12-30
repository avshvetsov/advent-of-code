package org.shvetsov.day13.dany;

import org.shvetsov.utils.Utils;

import java.util.Arrays;

import static org.shvetsov.day13.dany.MirrorFinder.calculateMirrorScore;
import static org.shvetsov.day13.dany.MirrorFinder.calculateMirrorScorePart2;

public class Day13Task {

    public static void main(String[] args) {

//        String[] input = getInput("day13_data.txt");
//
//        long result1 = part1(input);
//        long result2 = part2(input);
//
//        System.out.println("Result for part1: " + result1);
//        System.out.println("Result for part2: " + result2);

    }

    public static int part1(String[] input) {
        int result = 0;

        for (int i = 0; i < input.length; i++) {
            int j = i;
            int start = i;
            while (j < input.length && !input[j].isEmpty()) {
                j++;
            }
            int end = j;
            String[] framesString = Arrays.copyOfRange(input, start, end);
            char[][] frames = Utils.stringArrayToCharsDany(framesString);
            result += calculateMirrorScore(frames);
            i = j;
        }

        return result;
    }

    public static int part2(String[] input) {
        int result = 0;

        for (int i = 0; i < input.length; i++) {
            int j = i;
            int start = i;
            while (j < input.length && !input[j].isEmpty()) {
                j++;
            }
            int end = j;
            String[] framesString = Arrays.copyOfRange(input, start, end);
            char[][] frames = Utils.stringArrayToCharsDany(framesString);
            result += calculateMirrorScorePart2(frames);
            i = j;
        }

        return result;
    }

}
