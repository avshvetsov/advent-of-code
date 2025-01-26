package org.shvetsov.day14;

import org.apache.commons.lang3.StringUtils;
import org.shvetsov.utils.Point;
import org.shvetsov.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/14">Day 14</a>
 */
public class Day14 {

    public long partOne(List<String> input, int[] boundary) {
        int PART_1_SECONDS = 100;
        List<Robot> robots = new ArrayList<>();
        for (String s : input) {
            int[] position = Arrays.stream(StringUtils.substringBetween(s, "p=", " v=").split(",")).mapToInt(Integer::parseInt).toArray();
            int[] velocity = Arrays.stream(StringUtils.substringAfter(s, " v=").split(",")).mapToInt(Integer::parseInt).toArray();
            robots.add(new Robot(new Point(position[1], position[0]), new Point(velocity[1], velocity[0])));
        }
        for (int i = 0; i < PART_1_SECONDS; i++) {
            for (Robot robot : robots) {
                robot.move(boundary);
            }
        }
        int[] quadrants = new int[4];
        for (Robot robot : robots) {
            int quadrant = robot.getQuadrant(boundary);
            if (quadrant != -1) {
                quadrants[quadrant]++;
            }
        }
        return Arrays.stream(quadrants).asLongStream().reduce(1, (a, b) -> a * b);
    }

    //дивлячись на позиції роботів при їх друку, помітив що:
    //починаючи з 13 секунди, кожні 101 секунду роботи збираються у вертикальну смугу
    //починаючи з 65 секунди, кожні 103 секунди роботи збираються у горизонтальну смугу
    //виходячи з цього потрібно було вирішити рівняння у цілих числах, для знаходження секунди, коли вони перетнуться
    public long partTwo(List<String> input, int[] boundary) {
        int i = 0;
        while ((101 * i - 52) % 103 != 0) {
            i++;
        }
        long result = 101L * i + 13;

        List<Robot> robots = new ArrayList<>();
        for (String s : input) {
            int[] position = Arrays.stream(StringUtils.substringBetween(s, "p=", " v=").split(",")).mapToInt(Integer::parseInt).toArray();
            int[] velocity = Arrays.stream(StringUtils.substringAfter(s, " v=").split(",")).mapToInt(Integer::parseInt).toArray();
            robots.add(new Robot(new Point(position[1], position[0]), new Point(velocity[1], velocity[0])));
        }
        for (int j = 1; j < 8000; j++) {
            char[][] room = new char[boundary[1]][boundary[0]];
            for (char[] row : room) {
                Arrays.fill(row, '.');
            }
            for (Robot robot : robots) {
                robot.move(boundary);
                room[robot.getPosition().r()][robot.getPosition().c()] = '#';
            }
            if (j == result) {
                System.out.println("Second: " + j);
                PrintUtils.printArray(room);
            }
        }

        return result;
    }


    public void partTwoPrinter(List<String> input, int[] boundary) {
        List<Robot> robots = new ArrayList<>();
        for (String s : input) {
            int[] position = Arrays.stream(StringUtils.substringBetween(s, "p=", " v=").split(",")).mapToInt(Integer::parseInt).toArray();
            int[] velocity = Arrays.stream(StringUtils.substringAfter(s, " v=").split(",")).mapToInt(Integer::parseInt).toArray();
            robots.add(new Robot(new Point(position[1], position[0]), new Point(velocity[1], velocity[0])));
        }
        for (int i = 1; i < 200; i++) {
            char[][] room = new char[boundary[1]][boundary[0]];
            for (char[] row : room) {
                Arrays.fill(row, '.');
            }
            for (Robot robot : robots) {
                robot.move(boundary);
                room[robot.getPosition().r()][robot.getPosition().c()] = '#';
            }
            if (i%101 == 13 || i%103 == 65) {
                System.out.println("Second: " + i);
                PrintUtils.printArray(room);
            }
        }
    }

}