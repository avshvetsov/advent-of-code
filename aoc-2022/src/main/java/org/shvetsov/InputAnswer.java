package org.shvetsov;

import lombok.Getter;
import org.shvetsov.utils.Utils;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public enum InputAnswer {

    DAY_1_PART_1_EXAMPLE("1", 1, null, InputType.EXAMPLE, 24000L),
    DAY_1_PART_2_EXAMPLE("1", 2, null, InputType.EXAMPLE, 45000L),
    DAY_1_PART_1_REAL_ANTON("1", 1, "Anton", InputType.REAL, 69795L),
    DAY_1_PART_2_REAL_ANTON("1", 2, "Anton", InputType.REAL, 208437L),

    DAY_2_PART_1_EXAMPLE("2", 1, null, InputType.EXAMPLE, 15L),
    DAY_2_PART_2_EXAMPLE("2", 2, null, InputType.EXAMPLE, 12L),
    DAY_2_PART_1_REAL_ANTON("2", 1, "Anton", InputType.REAL, 14297L),
    DAY_2_PART_2_REAL_ANTON("2", 2, "Anton", InputType.REAL, 10498L),

    DAY_3_PART_1_EXAMPLE("3", 1, null, InputType.EXAMPLE, 157L),
    DAY_3_PART_2_EXAMPLE("3", 2, null, InputType.EXAMPLE, 70L),
    DAY_3_PART_1_REAL_ANTON("3", 1, "Anton", InputType.REAL, 8233L),
    DAY_3_PART_2_REAL_ANTON("3", 2, "Anton", InputType.REAL, 2821L),

    DAY_4_PART_1_EXAMPLE("4", 1, null, InputType.EXAMPLE, 2L),
    DAY_4_PART_2_EXAMPLE("4", 2, null, InputType.EXAMPLE, 4L),
    DAY_4_PART_1_REAL_ANTON("4", 1, "Anton", InputType.REAL, 456L),
    DAY_4_PART_2_REAL_ANTON("4", 2, "Anton", InputType.REAL, 808L),

    DAY_5_PART_1_EXAMPLE("5", 1, null, InputType.EXAMPLE, "CMZ"),
    DAY_5_PART_2_EXAMPLE("5", 2, null, InputType.EXAMPLE, "MCD"),
    DAY_5_PART_1_REAL_ANTON("5", 1, "Anton", InputType.REAL, "VJSFHWGFT"),
    DAY_5_PART_2_REAL_ANTON("5", 2, "Anton", InputType.REAL, "LCTQFBVZV"),

    DAY_6_PART_1_1_EXAMPLE("6", "1", 1, null, InputType.EXAMPLE, 7L),
    DAY_6_PART_1_2_EXAMPLE("6", "2", 1, null, InputType.EXAMPLE, 5L),
    DAY_6_PART_1_3_EXAMPLE("6", "3", 1, null, InputType.EXAMPLE, 6L),
    DAY_6_PART_1_4_EXAMPLE("6", "4", 1, null, InputType.EXAMPLE, 10L),
    DAY_6_PART_1_5_EXAMPLE("6", "5", 1, null, InputType.EXAMPLE, 11L),
    DAY_6_PART_2_1_EXAMPLE("6", "1", 1, null, InputType.EXAMPLE, 19L),
    DAY_6_PART_2_2_EXAMPLE("6", "2", 1, null, InputType.EXAMPLE, 23L),
    DAY_6_PART_2_3_EXAMPLE("6", "3", 1, null, InputType.EXAMPLE, 23L),
    DAY_6_PART_2_4_EXAMPLE("6", "4", 1, null, InputType.EXAMPLE, 29L),
    DAY_6_PART_2_5_EXAMPLE("6", "5", 1, null, InputType.EXAMPLE, 26L),
    DAY_6_PART_1_REAL_ANTON("6", 1, "Anton", InputType.REAL, 1175L),
    DAY_6_PART_2_REAL_ANTON("6", 2, "Anton", InputType.REAL, 3217L),

    DAY_7_PART_1_EXAMPLE("7", 1, null, InputType.EXAMPLE, 95437L),
    DAY_7_PART_2_EXAMPLE("7", 2, null, InputType.EXAMPLE, 24933642L),
    DAY_7_PART_1_REAL_ANTON("7", 1, "Anton", InputType.REAL, 1611443L),
    DAY_7_PART_2_REAL_ANTON("7", 2, "Anton", InputType.REAL, 2086088L),

    DAY_8_PART_1_EXAMPLE("8", 1, null, InputType.EXAMPLE, 21L),
    DAY_8_PART_2_EXAMPLE("8", 2, null, InputType.EXAMPLE, 8L),
    DAY_8_PART_1_REAL_ANTON("8", 1, "Anton", InputType.REAL, 1854L),
    DAY_8_PART_2_REAL_ANTON("8", 2, "Anton", InputType.REAL, 527340L),

    DAY_9_PART_1_EXAMPLE("9", 1, null, InputType.EXAMPLE, 13L),
    DAY_9_PART_2_EXAMPLE("9", 2, null, InputType.EXAMPLE, 1L),
    DAY_9_PART_2_1_EXAMPLE("9", "1", 2, null, InputType.EXAMPLE, 36L),
    DAY_9_PART_1_REAL_ANTON("9", 1, "Anton", InputType.REAL, 6563L),
    DAY_9_PART_2_REAL_ANTON("9", 2, "Anton", InputType.REAL, 2653L),

    DAY_10_PART_1_EXAMPLE("10", 1, null, InputType.EXAMPLE, 13140L),
    DAY_10_PART_2_EXAMPLE("10", 2, null, InputType.EXAMPLE, Utils.parseInputByNewLine("src/main/resources/day10/result 10-2 example.txt")),
    DAY_10_PART_1_REAL_ANTON("10", 1, "Anton", InputType.REAL, 12560L),
    DAY_10_PART_2_REAL_ANTON("10", 2, "Anton", InputType.REAL, Utils.parseInputByNewLine("src/main/resources/day10/result 10-2.txt")),

    DAY_11_PART_1_EXAMPLE("11", 1, null, InputType.EXAMPLE, 10_605L),
    DAY_11_PART_2_EXAMPLE("11", 2, null, InputType.EXAMPLE, 2_713_310_158L),
    DAY_11_PART_1_REAL_ANTON("11", 1, "Anton", InputType.REAL, 113_220L),
    DAY_11_PART_2_REAL_ANTON("11", 2, "Anton", InputType.REAL, 30_599_555_965L),

    DAY_12_PART_1_EXAMPLE("12", 1, null, InputType.EXAMPLE, 31L),
    DAY_12_PART_2_EXAMPLE("12", 2, null, InputType.EXAMPLE, 29L),
    DAY_12_PART_1_REAL_ANTON("12", 1, "Anton", InputType.REAL, 447L),
    DAY_12_PART_2_REAL_ANTON("12", 2, "Anton", InputType.REAL, 446L),

    DAY_13_PART_1_EXAMPLE("13", 1, null, InputType.EXAMPLE, 13L),
    DAY_13_PART_2_EXAMPLE("13", 2, null, InputType.EXAMPLE, 140L),
    DAY_13_PART_1_REAL_ANTON("13", 1, "Anton", InputType.REAL, 6656L),
    DAY_13_PART_2_REAL_ANTON("13", 2, "Anton", InputType.REAL, 19716L),

    DAY_14_PART_1_EXAMPLE("14", 1, null, InputType.EXAMPLE, 24L),
    DAY_14_PART_2_EXAMPLE("14", 2, null, InputType.EXAMPLE, 93L),
    DAY_14_PART_1_REAL_ANTON("14", 1, "Anton", InputType.REAL, 768L),
    DAY_14_PART_2_REAL_ANTON("14", 2, "Anton", InputType.REAL, 26686L),

    DAY_15_PART_1_EXAMPLE("15", 1, null, InputType.EXAMPLE, 26L, 10),
    DAY_15_PART_2_EXAMPLE("15", 2, null, InputType.EXAMPLE, 56000011L, 20),
    DAY_15_PART_1_REAL_ANTON("15", 1, "Anton", InputType.REAL, 4_582_667L, 2000000),
    DAY_15_PART_2_REAL_ANTON("15", 2, "Anton", InputType.REAL, 10_961_118_625_406L, 4000000),

    DAY_16_PART_1_EXAMPLE("16", 1, null, InputType.EXAMPLE, 1651L),
    DAY_16_PART_2_EXAMPLE("16", 2, null, InputType.EXAMPLE, 1707L),
    DAY_16_PART_1_REAL_ANTON("16", 1, "Anton", InputType.REAL, 1653L),
    DAY_16_PART_2_REAL_ANTON("16", 2, "Anton", InputType.REAL, 2223L),

    DAY_17_PART_1_EXAMPLE("17", 1, null, InputType.EXAMPLE, 3068L, 2022),
    DAY_17_PART_2_EXAMPLE("17", 2, null, InputType.EXAMPLE, 1514285714288L, 1000000000000L),
    DAY_17_PART_1_REAL_ANTON("17", 1, "Anton", InputType.REAL, 3186L, 2022),
    DAY_17_PART_2_REAL_ANTON("17", 2, "Anton", InputType.REAL, -1L, 1000000000000L),

    DAY_18_PART_1_EXAMPLE("18", 1, null, InputType.EXAMPLE, -1L),
    DAY_18_PART_2_EXAMPLE("18", 2, null, InputType.EXAMPLE, -1L),
    DAY_18_PART_1_REAL_ANTON("18", 1, "Anton", InputType.REAL, -1L),
    DAY_18_PART_2_REAL_ANTON("18", 2, "Anton", InputType.REAL, -1L),

    DAY_19_PART_1_EXAMPLE("19", 1, null, InputType.EXAMPLE, -1L),
    DAY_19_PART_2_EXAMPLE("19", 2, null, InputType.EXAMPLE, -1L),
    DAY_19_PART_1_REAL_ANTON("19", 1, "Anton", InputType.REAL, -1L),
    DAY_19_PART_2_REAL_ANTON("19", 2, "Anton", InputType.REAL, -1L),

    DAY_20_PART_1_EXAMPLE("20", 1, null, InputType.EXAMPLE, -1L),
    DAY_20_PART_2_EXAMPLE("20", 2, null, InputType.EXAMPLE, -1L),
    DAY_20_PART_1_REAL_ANTON("20", 1, "Anton", InputType.REAL, -1L),
    DAY_20_PART_2_REAL_ANTON("20", 2, "Anton", InputType.REAL, -1L),

    DAY_21_PART_1_EXAMPLE("21", 1, null, InputType.EXAMPLE, -1L),
    DAY_21_PART_2_EXAMPLE("21", 2, null, InputType.EXAMPLE, -1L),
    DAY_21_PART_1_REAL_ANTON("21", 1, "Anton", InputType.REAL, -1L),
    DAY_21_PART_2_REAL_ANTON("21", 2, "Anton", InputType.REAL, -1L),

    DAY_22_PART_1_EXAMPLE("22", 1, null, InputType.EXAMPLE, -1L),
    DAY_22_PART_2_EXAMPLE("22", 2, null, InputType.EXAMPLE, -1L),
    DAY_22_PART_1_REAL_ANTON("22", 1, "Anton", InputType.REAL, -1L),
    DAY_22_PART_2_REAL_ANTON("22", 2, "Anton", InputType.REAL, -1L),

    DAY_23_PART_1_EXAMPLE("23", 1, null, InputType.EXAMPLE, -1L),
    DAY_23_PART_2_EXAMPLE("23", 2, null, InputType.EXAMPLE, -1L),
    DAY_23_PART_1_REAL_ANTON("23", 1, "Anton", InputType.REAL, -1L),
    DAY_23_PART_2_REAL_ANTON("23", 2, "Anton", InputType.REAL, -1L),

    DAY_24_PART_1_EXAMPLE("24", 1, null, InputType.EXAMPLE, -1L),
    DAY_24_PART_2_EXAMPLE("24", 2, null, InputType.EXAMPLE, -1L),
    DAY_24_PART_1_REAL_ANTON("24", 1, "Anton", InputType.REAL, -1L),
    DAY_24_PART_2_REAL_ANTON("24", 2, "Anton", InputType.REAL, -1L),

    DAY_25_PART_1_EXAMPLE("25", 1, null, InputType.EXAMPLE, -1L),
    DAY_25_PART_2_EXAMPLE("25", 2, null, InputType.EXAMPLE, -1L),
    DAY_25_PART_1_REAL_ANTON("25", 1, "Anton", InputType.REAL, -1L),
    DAY_25_PART_2_REAL_ANTON("25", 2, "Anton", InputType.REAL, -1L),
    ;

    private static final String RESOURCES = "src/main/resources/";

    private final String day;
    private String subInputNum;
    private final int part;
    private final String user;
    private final InputType inputType;
    @Getter
    private final Object[] additionalInput;
    @Getter
    private final Object answer;

    InputAnswer(String day, String subInputNum, int part, String user, InputType inputType, Object answer, Object... additionalInput) {
        this.day = day;
        this.subInputNum = subInputNum;
        this.part = part;
        this.user = user;
        this.inputType = inputType;
        this.answer = answer;
        this.additionalInput = additionalInput;
    }

    InputAnswer(String day, int part, String user, InputType inputType, Object answer, Object... additionalInput) {
        this.day = day;
        this.part = part;
        this.user = user;
        this.inputType = inputType;
        this.additionalInput = additionalInput;
        this.answer = answer;
    }

    public String getInput(InputFormat inputFormat) {
        StringBuilder res = new StringBuilder();
        if (inputFormat.equals(InputFormat.PATH)) {
            res.append(RESOURCES);
        }
        res.append("day").append(day).append("/").append(inputType.namePattern).append("_").append(day);
        if (subInputNum != null) {
            res.append("-").append(subInputNum);
        }
        if (inputType.equals(InputType.REAL)) {
            res.append("_").append(user);
        }
        res.append(".txt");
        return res.toString();
    }

    public enum InputFormat {
        PATH,
        NAME
    }

    public enum InputType {
        EXAMPLE("example"),
        REAL("input");

        public final String namePattern;

        InputType(String namePattern) {
            this.namePattern = namePattern;
        }


    }

    static class Constants {
        static final String IGNORED = "ignored";
    }

}
