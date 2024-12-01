package org.shvetsov.day15;

import com.google.common.collect.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.shvetsov.utils.Point;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2022/day/15">Day 15</a>
 */
public class Day15 {

    public long partOne(List<String> input, int targetRow) {
        Set<Pair<Point, Point>> sensorBeaconPair = new HashSet<>();
        Set<Integer> allOccupiedPointsInTargetRow = new HashSet<>();
        for (String s : input) {
            String[] sensorBeacon = s.split(":");
            Point sensor = new Point(
                    Integer.parseInt(StringUtils.substringAfter(sensorBeacon[0], "y=")),
                    Integer.parseInt(StringUtils.substringBetween(sensorBeacon[0], "x=", ","))
            );
            Point beacon = new Point(
                    Integer.parseInt(StringUtils.substringAfter(sensorBeacon[1], "y=")),
                    Integer.parseInt(StringUtils.substringBetween(sensorBeacon[1], "x=", ","))
            );
            sensorBeaconPair.add(Pair.of(sensor, beacon));
            if (sensor.r() == targetRow) {
                allOccupiedPointsInTargetRow.add(sensor.c());
            }
            if (beacon.r() == targetRow) {
                allOccupiedPointsInTargetRow.add(beacon.c());
            }
        }

        RangeSet<Integer> impossiblePositions = TreeRangeSet.create();
        for (Pair<Point, Point> sensorBeacon : sensorBeaconPair) {
            Point sensor = sensorBeacon.getLeft();
            Point beacon = sensorBeacon.getRight();
            int distance = sensor.distance(beacon);
            int diff = Math.abs(targetRow - sensor.r());
            if (distance >= diff) {
                impossiblePositions.add(Range.closed(sensor.c() - (distance - diff), sensor.c() + (distance - diff)));
            }
        }
        for (Integer c : allOccupiedPointsInTargetRow) {
            impossiblePositions.remove(Range.singleton(c));
        }

        return impossiblePositions.asRanges().stream()
                .mapToInt(range -> ContiguousSet.create(range, DiscreteDomain.integers()).size())
                .sum();
    }


    public long partTwo(List<String> input, int bound) {
        Set<Pair<Point, Point>> sensorBeaconPair = new HashSet<>();
        for (String s : input) {
            String[] sensorBeacon = s.split(":");
            Point sensor = new Point(
                    Integer.parseInt(StringUtils.substringAfter(sensorBeacon[0], "y=")),
                    Integer.parseInt(StringUtils.substringBetween(sensorBeacon[0], "x=", ","))
            );
            Point beacon = new Point(
                    Integer.parseInt(StringUtils.substringAfter(sensorBeacon[1], "y=")),
                    Integer.parseInt(StringUtils.substringBetween(sensorBeacon[1], "x=", ","))
            );
            sensorBeaconPair.add(Pair.of(sensor, beacon));
        }

        Map<Integer, RangeSet<Integer>> rowRangeSetMap = new HashMap<>();
        for (Pair<Point, Point> sensorBeacon : sensorBeaconPair) {
            Point sensor = sensorBeacon.getLeft();
            Point beacon = sensorBeacon.getRight();
            int distance = sensor.distance(beacon);
            int i = distance;
            while (i >= 0) {
                if (sensor.r() - i >= 0 && sensor.r() - i <= bound) {
                    rowRangeSetMap.computeIfAbsent(sensor.r() - i,
                            key -> TreeRangeSet.create()).add(Range.closed(sensor.c() - (distance - i), sensor.c() + (distance - i)));
                }
                if (sensor.r() + i >= 0 && sensor.r() + i <= bound) {
                    rowRangeSetMap.computeIfAbsent(sensor.r() + i,
                            key -> TreeRangeSet.create()).add(Range.closed(sensor.c() - (distance - i), sensor.c() + (distance - i)));
                }
                i--;
            }
        }

        for (Map.Entry<Integer, RangeSet<Integer>> entry : rowRangeSetMap.entrySet()) {
            RangeSet<Integer> rangeSet = entry.getValue();
            if (rangeSet.asRanges().size() > 1) {
                Set<Range<Integer>> ranges = rangeSet.asRanges();
                Iterator<Range<Integer>> iterator = ranges.iterator();
                Range<Integer> first = iterator.next();
                do {
                    Range<Integer> second = iterator.next();
                    if (second.lowerEndpoint() - first.upperEndpoint() == 2) {
                        return (second.lowerEndpoint() - 1) * 4_000_000L + entry.getKey();
                    }
                } while (iterator.hasNext());
            }
        }
        throw new RuntimeException("Beacon not found");
    }

    public long partTwoSingleRangeSet(List<String> input, int bound) {
        Set<Pair<Point, Point>> sensorBeaconPair = new HashSet<>();
        for (String s : input) {
            String[] sensorBeacon = s.split(":");
            Point sensor = new Point(
                    Integer.parseInt(StringUtils.substringAfter(sensorBeacon[0], "y=")),
                    Integer.parseInt(StringUtils.substringBetween(sensorBeacon[0], "x=", ","))
            );
            Point beacon = new Point(
                    Integer.parseInt(StringUtils.substringAfter(sensorBeacon[1], "y=")),
                    Integer.parseInt(StringUtils.substringBetween(sensorBeacon[1], "x=", ","))
            );
            sensorBeaconPair.add(Pair.of(sensor, beacon));
        }

        RangeSet<Long> rangeSet = TreeRangeSet.create();
        for (Pair<Point, Point> sensorBeacon : sensorBeaconPair) {
            Point sensor = sensorBeacon.getLeft();
            Point beacon = sensorBeacon.getRight();
            int distance = sensor.distance(beacon);
            int i = distance;
            while (i >= 0) {
                int row = sensor.r() - i;
                if (row >= 0 && row <= bound) {
                    rangeSet.add(Range.closed((long) row * bound + Math.max(sensor.c() - (distance - i), 0),
                            (long) row * bound + Math.min(sensor.c() + (distance - i), bound)));
                }
                row = sensor.r() + i;
                if (row >= 0 && row <= bound) {
                    rangeSet.add(Range.closed((long) row * bound + Math.max(sensor.c() - (distance - i), 0),
                            (long) row * bound + Math.min(sensor.c() + (distance - i), bound)));
                }
                i--;
            }
        }

        Set<Range<Long>> ranges = rangeSet.asRanges();
        Iterator<Range<Long>> iterator = ranges.iterator();
        Range<Long> first = iterator.next();
        do {
            Range<Long> second = iterator.next();
            if (second.lowerEndpoint() - first.upperEndpoint() == 2) {
                return (second.lowerEndpoint() - 1) / (bound) + ((second.lowerEndpoint() - 1) % (bound)) * 4000000;
            }
        } while (iterator.hasNext());
        throw new RuntimeException("Beacon not found");
    }

}