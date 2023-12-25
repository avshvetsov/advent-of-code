package org.shvetsov.day12;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.shvetsov.InputAnswer;
import org.shvetsov.utils.Utils;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shvetsov.InputAnswer.InputFormat.PATH;

@Disabled
class Day12InefficientTest {

    private final Day12Inefficient day = new Day12Inefficient();

    //???.### 1,1,3
    @Test
    public void regexTest1() {
        String input = "#.#.###";

        String regex1 = "[.#]{3}\\.###";
        Pattern pattern1 = Pattern.compile(regex1);

        String regex2 = "\\.*#\\.+#\\.+###\\.*";
        Pattern pattern2 = Pattern.compile(regex2);

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        System.out.println(input + " : " + matcher1.matches());
        System.out.println(input + " : " + matcher2.matches());
    }

    //    .??..??...?##. 1,1,3
    @Test
    public void regexTest2() {
        String input = ".#....#...###";

        String regex1 = "\\.*\\.[.#]{2}\\.\\.[.#]{2}\\.\\.\\.[.#]##\\.*";
        Pattern pattern1 = Pattern.compile(regex1);

        String regex2 = "\\.*#{1}\\.+#{1}\\.+#{3}\\.*";
        Pattern pattern2 = Pattern.compile(regex2);

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        System.out.println(input + " : " + matcher1.matches());
        System.out.println(input + " : " + matcher2.matches());
    }

    @Test
    public void combinatorics() {
        String replace = String.format("%" + 7 + "s", Integer.toBinaryString(1)).replace(' ', '0');
        System.out.println(replace);
    }

    @Test
    public void test() {
        int length = 7;
        IntStream.range(0, (int) Math.pow(2, length))
//                .mapToObj(i -> String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0'))
                .mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), length, '0'))
                .filter(s -> s.chars().filter(c -> c == '1').count() == 3)
                .map(s -> s.replaceAll("0", ".").replaceAll("1", "#"))
                .forEach(System.out::println);
    }

    //?###???????? 3,2,1
    @Test
    public void real() {
        String input = "?###????????";
        String regex = "\\.*#{3}\\.+#{2}\\.+#{1}\\.*";
        Pattern pattern = Pattern.compile(regex);
        int brokenTotal = 6;
        int brokenPresent = (int) input.chars().filter(c -> c == '#').count();
        int unknown = (int) input.chars().filter(c -> c == '?').count();
        int brokenFromUnknown = brokenTotal - brokenPresent;

        StringBuilder sb = new StringBuilder();
        int n = 1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '?') {
                sb.append(input.charAt(i));
            } else {
                sb.append("%c");
                n++;
            }
        }
        String formattedInput = sb.toString();


        long arrangements = IntStream.range(0, (int) Math.pow(2, unknown))
//                .mapToObj(i -> String.format("%" + length + "s", Integer.toBinaryString(i)).replace(' ', '0'))
                .mapToObj(i -> StringUtils.leftPad(Integer.toBinaryString(i), unknown, '0'))
                .filter(s -> s.chars().filter(c -> c == '1').count() == brokenFromUnknown)
                .map(s -> s.replaceAll("0", ".").replaceAll("1", "#"))
                .map(s -> String.format(formattedInput, ArrayUtils.toObject(s.toCharArray())))
                .filter(s -> pattern.matcher(s).matches())
                .count();
        assertThat(arrangements).isEqualTo(10);
    }


    @ParameterizedTest
    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partOneInput(InputAnswer param) {
        long result = day.partOneAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_1.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partOneInputDany(InputAnswer param) {
//        int result = Day12Task.part1(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer());
//    }


    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_2_EXAMPLE"}, mode = EnumSource.Mode.MATCH_ALL)
    public void partTwoInput(InputAnswer param) {
        BigInteger result = day.partTwoAnton(Utils.parseInputByNewLine(param.getInput(PATH)));
        System.out.println(result);
        assertThat(result).isEqualTo(param.getAnswer());
    }

//    @ParameterizedTest
//    @EnumSource(value = InputAnswer.class, names = {"DAY_12_PART_2.+"}, mode = EnumSource.Mode.MATCH_ALL)
//    public void partTwoInputDany(InputAnswer param) {
//        int result = Day12Task.part2(Utils.parseInputDany(param.getInput(NAME)));
//        System.out.println(result);
//        assertThat(result).isEqualTo(param.getAnswer());
//    }


}