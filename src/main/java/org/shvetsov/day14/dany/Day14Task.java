package org.shvetsov.day14.dany;


import org.shvetsov.utils.Utils;

public class Day14Task {

    public static void main(String[] args) {
//
//        String[] input = getInput("day14_data.txt");
//
//        long result1 = part1(input);
//        long result2 = part2(input);
//
//        System.out.println("Result for part1: " + result1);
//        System.out.println("Result for part2: " + result2);

    }

    public static long part1(String[] input) {
        char[][] chars = Utils.stringArrayToCharsDany(input);
        ControlPanel controlPanel = new ControlPanel(chars);
        Tilter tilter = new Tilter(controlPanel);
        tilter.tilt(TiltDirection.NORTH);
        return controlPanel.calculatePressure();
    }

    public static long part2(String[] input) {
        long result = 0;

        return result;
    }

}
