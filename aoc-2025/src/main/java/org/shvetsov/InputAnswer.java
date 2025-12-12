package org.shvetsov;

import lombok.Getter;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public enum InputAnswer {

    // Day 1
    DAY_1_PART_1_EXAMPLE("1", 1, null, InputType.EXAMPLE, 3L),
    DAY_1_PART_2_EXAMPLE("1", 2, null, InputType.EXAMPLE, 6L),
    DAY_1_PART_1_REAL_ANTON("1", 1, "Anton", InputType.REAL, 980L),
    DAY_1_PART_2_REAL_ANTON("1", 2, "Anton", InputType.REAL, 5961L),

    // Day 2 
    DAY_2_PART_1_EXAMPLE("2", 1, null, InputType.EXAMPLE, 1227775554L),
    DAY_2_PART_2_EXAMPLE("2", 2, null, InputType.EXAMPLE, 4174379265L),
    DAY_2_PART_1_REAL_ANTON("2", 1, "Anton", InputType.REAL, 19219508902L),
    DAY_2_PART_2_REAL_ANTON("2", 2, "Anton", InputType.REAL, 27180728081L),

    // Day 3
    DAY_3_PART_1_EXAMPLE("3", 1, null, InputType.EXAMPLE, 357L),
    DAY_3_PART_2_EXAMPLE("3", 2, null, InputType.EXAMPLE, 3121910778619L),
    DAY_3_PART_1_REAL_ANTON("3", 1, "Anton", InputType.REAL, 17412L),
    DAY_3_PART_2_REAL_ANTON("3", 2, "Anton", InputType.REAL, 172681562473501L),

    // Day 4
    DAY_4_PART_1_EXAMPLE("4", 1, null, InputType.EXAMPLE, 13L),
    DAY_4_PART_2_EXAMPLE("4", 2, null, InputType.EXAMPLE, 43L),
    DAY_4_PART_1_REAL_ANTON("4", 1, "Anton", InputType.REAL, 1435L),
    DAY_4_PART_2_REAL_ANTON("4", 2, "Anton", InputType.REAL, 8623L),

    // Day 5
    DAY_5_PART_1_EXAMPLE("5", 1, null, InputType.EXAMPLE, 3L),
    DAY_5_PART_2_EXAMPLE("5", 2, null, InputType.EXAMPLE, 14L),
    DAY_5_PART_1_REAL_ANTON("5", 1, "Anton", InputType.REAL, 789L),
    DAY_5_PART_2_REAL_ANTON("5", 2, "Anton", InputType.REAL, 343329651880509L),

    // Day 6
    DAY_6_PART_1_EXAMPLE("6", 1, null, InputType.EXAMPLE, 4277556L),
    DAY_6_PART_2_EXAMPLE("6", 2, null, InputType.EXAMPLE, 3263827L),
    DAY_6_PART_1_REAL_ANTON("6", 1, "Anton", InputType.REAL, 6295830249262L),
    DAY_6_PART_2_REAL_ANTON("6", 2, "Anton", InputType.REAL, 9194682052782L),

    // Day 7
    DAY_7_PART_1_EXAMPLE("7", 1, null, InputType.EXAMPLE, 21L),
    DAY_7_PART_2_EXAMPLE("7", 2, null, InputType.EXAMPLE, 40L),
    DAY_7_PART_1_REAL_ANTON("7", 1, "Anton", InputType.REAL, 1550L),
    DAY_7_PART_2_REAL_ANTON("7", 2, "Anton", InputType.REAL, 9897897326778L),

    // Day 8
    DAY_8_PART_1_EXAMPLE("8", 1, null, InputType.EXAMPLE, 40L, 10),
    DAY_8_PART_2_EXAMPLE("8", 2, null, InputType.EXAMPLE, 25272L),
    DAY_8_PART_1_REAL_ANTON("8", 1, "Anton", InputType.REAL, 57564L, 1000),
    DAY_8_PART_2_REAL_ANTON("8", 2, "Anton", InputType.REAL, 133296744L),

    // Day 9
    DAY_9_PART_1_EXAMPLE("9", 1, null, InputType.EXAMPLE, 50L),
    DAY_9_PART_2_EXAMPLE("9", 2, null, InputType.EXAMPLE, 24L),
    DAY_9_PART_1_REAL_ANTON("9", 1, "Anton", InputType.REAL, 4738108384L),
    DAY_9_PART_2_REAL_ANTON("9", 2, "Anton", InputType.REAL, -1L),

    // Day 10
    DAY_10_PART_1_EXAMPLE("10", 1, null, InputType.EXAMPLE, 7L),
    DAY_10_PART_2_EXAMPLE("10", 2, null, InputType.EXAMPLE, 33L),
    DAY_10_PART_1_REAL_ANTON("10", 1, "Anton", InputType.REAL, 522L),
    DAY_10_PART_2_REAL_ANTON("10", 2, "Anton", InputType.REAL, -1L),

    // Day 11
    DAY_11_PART_1_EXAMPLE("11", "1", 1, null, InputType.EXAMPLE, 5L),
    DAY_11_PART_2_EXAMPLE("11", "2", 2, null, InputType.EXAMPLE, 2L),
    DAY_11_PART_1_REAL_ANTON("11", 1, "Anton", InputType.REAL, 652L),
    DAY_11_PART_2_REAL_ANTON("11", 2, "Anton", InputType.REAL, 362956369749210L),

    // Day 12
    DAY_12_PART_1_EXAMPLE("12", 1, null, InputType.EXAMPLE, -1L),
    DAY_12_PART_2_EXAMPLE("12", 2, null, InputType.EXAMPLE, -1L),
    DAY_12_PART_1_REAL_ANTON("12", 1, "Anton", InputType.REAL, -1L),
    DAY_12_PART_2_REAL_ANTON("12", 2, "Anton", InputType.REAL, -1L),
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
