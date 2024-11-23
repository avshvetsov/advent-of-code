package org.shvetsov;

import lombok.Getter;

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

    DAY_3_PART_1_EXAMPLE("3", 1, null, InputType.EXAMPLE, -1L),
    DAY_3_PART_2_EXAMPLE("3", 2, null, InputType.EXAMPLE, -1L),
    DAY_3_PART_1_REAL_ANTON("3", 1, "Anton", InputType.REAL, -1L),
    DAY_3_PART_2_REAL_ANTON("3", 2, "Anton", InputType.REAL, -1L),

    DAY_4_PART_1_EXAMPLE("4", 1, null, InputType.EXAMPLE, -1L),
    DAY_4_PART_2_EXAMPLE("4", 2, null, InputType.EXAMPLE, -1L),
    DAY_4_PART_1_REAL_ANTON("4", 1, "Anton", InputType.REAL, -1L),
    DAY_4_PART_2_REAL_ANTON("4", 2, "Anton", InputType.REAL, -1L),

    DAY_5_PART_1_EXAMPLE("5", 1, null, InputType.EXAMPLE, -1L),
    DAY_5_PART_2_EXAMPLE("5", 2, null, InputType.EXAMPLE, -1L),
    DAY_5_PART_1_REAL_ANTON("5", 1, "Anton", InputType.REAL, -1L),
    DAY_5_PART_2_REAL_ANTON("5", 2, "Anton", InputType.REAL, -1L),

    DAY_6_PART_1_EXAMPLE("6", 1, null, InputType.EXAMPLE, -1L),
    DAY_6_PART_2_EXAMPLE("6", 2, null, InputType.EXAMPLE, -1L),
    DAY_6_PART_1_REAL_ANTON("6", 1, "Anton", InputType.REAL, -1L),
    DAY_6_PART_2_REAL_ANTON("6", 2, "Anton", InputType.REAL, -1L),

    DAY_7_PART_1_EXAMPLE("7", 1, null, InputType.EXAMPLE, -1L),
    DAY_7_PART_2_EXAMPLE("7", 2, null, InputType.EXAMPLE, -1L),
    DAY_7_PART_1_REAL_ANTON("7", 1, "Anton", InputType.REAL, -1L),
    DAY_7_PART_2_REAL_ANTON("7", 2, "Anton", InputType.REAL, -1L),

    DAY_8_PART_1_EXAMPLE("8", 1, null, InputType.EXAMPLE, -1L),
    DAY_8_PART_2_EXAMPLE("8", 2, null, InputType.EXAMPLE, -1L),
    DAY_8_PART_1_REAL_ANTON("8", 1, "Anton", InputType.REAL, -1L),
    DAY_8_PART_2_REAL_ANTON("8", 2, "Anton", InputType.REAL, -1L),

    DAY_9_PART_1_EXAMPLE("9", 1, null, InputType.EXAMPLE, -1L),
    DAY_9_PART_2_EXAMPLE("9", 2, null, InputType.EXAMPLE, -1L),
    DAY_9_PART_1_REAL_ANTON("9", 1, "Anton", InputType.REAL, -1L),
    DAY_9_PART_2_REAL_ANTON("9", 2, "Anton", InputType.REAL, -1L),

    DAY_10_PART_1_EXAMPLE("10", 1, null, InputType.EXAMPLE, -1L),
    DAY_10_PART_2_EXAMPLE("10", 2, null, InputType.EXAMPLE, -1L),
    DAY_10_PART_1_REAL_ANTON("10", 1, "Anton", InputType.REAL, -1L),
    DAY_10_PART_2_REAL_ANTON("10", 2, "Anton", InputType.REAL, -1L),

    DAY_11_PART_1_EXAMPLE("11", 1, null, InputType.EXAMPLE, -1L),
    DAY_11_PART_2_EXAMPLE("11", 2, null, InputType.EXAMPLE, -1L),
    DAY_11_PART_1_REAL_ANTON("11", 1, "Anton", InputType.REAL, -1L),
    DAY_11_PART_2_REAL_ANTON("11", 2, "Anton", InputType.REAL, -1L),

    DAY_12_PART_1_EXAMPLE("12", 1, null, InputType.EXAMPLE, -1L),
    DAY_12_PART_2_EXAMPLE("12", 2, null, InputType.EXAMPLE, -1L),
    DAY_12_PART_1_REAL_ANTON("12", 1, "Anton", InputType.REAL, -1L),
    DAY_12_PART_2_REAL_ANTON("12", 2, "Anton", InputType.REAL, -1L),

    DAY_13_PART_1_EXAMPLE("13", 1, null, InputType.EXAMPLE, -1L),
    DAY_13_PART_2_EXAMPLE("13", 2, null, InputType.EXAMPLE, -1L),
    DAY_13_PART_1_REAL_ANTON("13", 1, "Anton", InputType.REAL, -1L),
    DAY_13_PART_2_REAL_ANTON("13", 2, "Anton", InputType.REAL, -1L),

    DAY_14_PART_1_EXAMPLE("14", 1, null, InputType.EXAMPLE, -1L),
    DAY_14_PART_2_EXAMPLE("14", 2, null, InputType.EXAMPLE, -1L),
    DAY_14_PART_1_REAL_ANTON("14", 1, "Anton", InputType.REAL, -1L),
    DAY_14_PART_2_REAL_ANTON("14", 2, "Anton", InputType.REAL, -1L),

    DAY_15_PART_1_EXAMPLE("15", 1, null, InputType.EXAMPLE, -1L),
    DAY_15_PART_2_EXAMPLE("15", 2, null, InputType.EXAMPLE, -1L),
    DAY_15_PART_1_REAL_ANTON("15", 1, "Anton", InputType.REAL, -1L),
    DAY_15_PART_2_REAL_ANTON("15", 2, "Anton", InputType.REAL, -1L),

    DAY_16_PART_1_EXAMPLE("16", 1, null, InputType.EXAMPLE, -1L),
    DAY_16_PART_2_EXAMPLE("16", 2, null, InputType.EXAMPLE, -1L),
    DAY_16_PART_1_REAL_ANTON("16", 1, "Anton", InputType.REAL, -1L),
    DAY_16_PART_2_REAL_ANTON("16", 2, "Anton", InputType.REAL, -1L),

    DAY_17_PART_1_EXAMPLE("17", 1, null, InputType.EXAMPLE, -1L),
    DAY_17_PART_2_EXAMPLE("17", 2, null, InputType.EXAMPLE, -1L),
    DAY_17_PART_1_REAL_ANTON("17", 1, "Anton", InputType.REAL, -1L),
    DAY_17_PART_2_REAL_ANTON("17", 2, "Anton", InputType.REAL, -1L),

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
    private Object additionalInput = null;
    @Getter
    private final Number answer;

    InputAnswer(String day, int part, String user, InputType inputType, Number answer) {
        this.day = day;
        this.part = part;
        this.user = user;
        this.inputType = inputType;
        this.answer = answer;
    }

    InputAnswer(String day, String subInputNum, int part, String user, InputType inputType, Number answer) {
        this.day = day;
        this.subInputNum = subInputNum;
        this.part = part;
        this.user = user;
        this.inputType = inputType;
        this.answer = answer;
    }

    InputAnswer(String day, int part, String user, InputType inputType, Object additionalInput, Number answer) {
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

}
