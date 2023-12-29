package org.shvetsov.day15;

import one.util.streamex.EntryStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/15">Day 15</a>
 */
public class Day15 {

    public static void main(String[] args) {
        System.out.println(new CustomString("qp=3").hashCode());
        System.out.println(new CustomString("qp-").hashCode());
        System.out.println(new CustomString("qp").hashCode());
        System.out.println(new CustomString("cm").hashCode());
        System.out.println(new CustomString("rn").hashCode());
    }

    public int partOneAnton(List<String> inputList) {
        List<CustomString> list = Arrays.stream(inputList.get(0).split(",")).map(CustomString::new).toList();
        return list.stream().mapToInt(CustomString::hashCode).sum();
    }

    public int partTwoAnton(List<String> inputList) {
        int res = 0;
        Map<CustomString, Integer> boxes = new HashMap<>();
        Arrays.stream(inputList.get(0).split(","))
                .map(s -> {
                    if (s.contains("=")) {
                        return Triple.of(new CustomString(StringUtils.substringBefore(s, "=")), "=", Integer.parseInt(StringUtils.substringAfter(s, "=")));
                    } else {
                        return Triple.of(new CustomString(StringUtils.substringBefore(s, "-")), "-", 0);
                    }
                })
                .forEach(triple -> {
                    if (triple.getMiddle().equals("=")) {
                        boxes.put(triple.getLeft(), triple.getRight());
                    } else boxes.remove(triple.getLeft());
                });
        List<Map.Entry<CustomString, Integer>> boxesList = EntryStream.of(boxes).sortedByInt(entry -> entry.getKey().hashCode()).toList();
        int bucket = -1;
        int slot = -1;
        for (int i = 0; i < boxesList.size(); i++) {
            int hash = boxesList.get(i).getKey().hashCode();
            int focal = boxesList.get(i).getValue();
            if (hash != bucket) {
                bucket = hash;
                slot = 1;
            }
            res += (hash + 1) * slot * focal;
            slot++;
        }

        return res;
    }

    public static class CustomString {
        private final String string;

        public CustomString(String string) {
            this.string = string;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomString that = (CustomString) o;
            return Objects.equals(string, that.string);
        }

        @Override
        public int hashCode() {
            int res = 0;
            int[] asciiArray = string.chars().toArray();
            for (int i = 0; i < asciiArray.length; i++) {
                res = ((res + asciiArray[i]) * 17) % 256;
            }
            return res;
        }
    }

}