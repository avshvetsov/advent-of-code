package org.shvetsov.day24;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2023/day/24">Day 24</a>
 */
public class Day24 {

    public long partOneAnton(List<String> inputList, long[] targetArea) {
        List<HailstonePart1> hailstones = new ArrayList<>();
        for (String input : inputList) {
            hailstones.add(new HailstonePart1(input));
        }
        int result = 0;
        for (int i = 0; i < hailstones.size(); i++) {
            for (int j = i + 1; j < hailstones.size(); j++) {
                HailstonePart1 h1 = hailstones.get(i);
                HailstonePart1 h2 = hailstones.get(j);
                double[] point = calculateTraceIntersectionPoint(h1, h2);
                if (point[0] >= targetArea[0] && point[0] <= targetArea[1] && point[1] >= targetArea[0] && point[1] <= targetArea[1] &&
                    h1.vx > 0 == (point[0] - h1.x) > 0 && h2.vx > 0 == (point[0] - h2.x) > 0 && h1.vy > 0 == (point[1] - h1.y) > 0 && h2.vy > 0 == (point[1] - h2.y) > 0) {
                    result++;
                }
            }
        }
        return result;
    }

    private double[] calculateTraceIntersectionPoint(HailstonePart1 h1, HailstonePart1 h2) {
        double[] point = new double[2];
        point[0] = (h2.b - h1.b) / (h1.a - h2.a);
        point[1] = h1.a * point[0] + h1.b;
        return point;
    }

    public long partTwoAnton(List<String> inputList) {
        List<HailstonePart2> hailstones = new ArrayList<>();
        for (String input : inputList) {
            hailstones.add(new HailstonePart2(input));
        }
        //Эти расчеты базируются на hailstone, которые имеют одинаковую V по каждой из осей.
        //Т.е. для таких hailstone существует только небольшой набор возможных V камня, который смог бы сбить обе градины.
        //Этот набор - это делители расстояния между hailstone c одинаковой V (положительные и отрицательные).
        //Подставляя делители первой пары, подставляем их во вторую, третью, и т.д. пока не сузим количество вариантов до 1.
        Set<Long> xVelocities = calculateVelocities(hailstones, h -> h.vx, h -> h.x);
        Set<Long> yVelocities = calculateVelocities(hailstones, h -> h.vy, h -> h.y);
        Set<Long> zVelocities = calculateVelocities(hailstones, h -> h.vz, h -> h.z);

        if (xVelocities.size() == 1 && yVelocities.size() == 1 && zVelocities.size() == 1) {
            long vx = xVelocities.stream().findFirst().get();
            long vy = yVelocities.stream().findFirst().get();
            long vz = zVelocities.stream().findFirst().get();

            HailstonePart2 h1 = hailstones.get(0);
            HailstonePart2 h2 = hailstones.get(1);
            //выражаем функцию каждой координаты через начальную точку и V (x = x0 + Vx)
            //приравниваем функции для каждой координаты камня и hailstone (точка столкновения), получаем выражение x0 камня, через t столкновения с hailstone
            //выражаем координаты x0 и y0 камня через t1 и t2 (время столкновения с h1 и h2)
            //приравнивая x0 через t1 и x0 через t2, выражаем t1 через t2. Аналогично y0 через t1 и y0 через t2, так же получаем t1 через t2.
            //приравниваем t1 и находим значение t2
            //через подстановку t2, находим x, y, z
            double t2 = ((double)(h2.y - h1.y) / (h1.vy - vy) - (double)(h2.x - h1.x) / (h1.vx - vx)) / ((double)(h2.vx - vx) / (h1.vx - vx) - (double)(h2.vy - vy) / (h1.vy - vy));
            double x = h2.x + (h2.vx - vx) * t2;
            double y = h2.y + (h2.vy - vy) * t2;
            double z = h2.z + (h2.vz - vz) * t2;
            return Double.valueOf(x+y+z).longValue();
        } else {
            throw new IllegalStateException("This input should be solve another way");
        }
    }

    private Set<Long> calculateVelocities(List<HailstonePart2> hailstones, Function<HailstonePart2, Integer> velocityFunction, Function<HailstonePart2, Long> coordinateFunction) {
        Map<Integer, List<HailstonePart2>> xVelocityMap = hailstones.stream()
                .collect(Collectors.groupingBy(velocityFunction)).entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<Map.Entry<Integer, List<HailstonePart2>>> entries = xVelocityMap.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue().size(), Comparator.reverseOrder()))
                .toList();

        Set<Long> targetVelocities = findDivisors(coordinateFunction.apply(entries.getFirst().getValue().get(0)) - coordinateFunction.apply(entries.getFirst().getValue().get(1))).stream()
                .map(v -> v + entries.getFirst().getKey())
                .collect(Collectors.toSet());
        for (Map.Entry<Integer, List<HailstonePart2>> entry : entries) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                for (int j = i + 1; j < entry.getValue().size(); j++) {
                    HailstonePart2 h1 = entry.getValue().get(i);
                    HailstonePart2 h2 = entry.getValue().get(j);
                    long distance = coordinateFunction.apply(h1) - coordinateFunction.apply(h2);
                    targetVelocities = targetVelocities.stream()
                            .filter(v -> v - entry.getKey() != 0 && distance % (v - entry.getKey()) == 0)
                            .collect(Collectors.toSet());
                    if (targetVelocities.size() == 1) {
                        break;
                    }
                }
            }
        }
        return targetVelocities;
    }

    private Set<Long> findDivisors(long value) {
        Set<Long> result = new HashSet<>();
        for (long i = 1; i * i <= Math.abs(value); i++) {
            if (value % i == 0) {
                result.add(i);
                result.add(value/i);
                result.add(-i);
                result.add(-value/i);
            }
        }
        return result;
    }


}