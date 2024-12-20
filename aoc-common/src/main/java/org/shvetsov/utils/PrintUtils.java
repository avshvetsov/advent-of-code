package org.shvetsov.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PrintUtils {

    private void printRow(char[] row) {
        for (char i : row) {
            System.out.print(i);
        }
        System.out.println();
    }

    private void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
        }
        System.out.println();
    }

    public void printArray(char[][] array) {
        for (char[] row : array) {
            printRow(row);
        }
    }

    public void printArray(int[][] array) {
        for (int[] row : array) {
            printRow(row);
        }
    }
}
