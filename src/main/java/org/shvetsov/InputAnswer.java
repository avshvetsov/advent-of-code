package org.shvetsov;

import lombok.Getter;

import java.math.BigInteger;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public enum InputAnswer {
    DAY_6_PART_1_EXAMPLE("6",1,null, InputType.EXAMPLE, 288),
    DAY_6_PART_2_EXAMPLE("6",2,null, InputType.EXAMPLE, 71503L),
    DAY_6_PART_1_REAL_ANTON("6",1,"Anton", InputType.REAL, 503424),
    DAY_6_PART_2_REAL_ANTON("6",2,"Anton", InputType.REAL, 32607562L),
    DAY_6_PART_1_REAL_DANY("6",1,"Dany", InputType.REAL, 74698),
    DAY_6_PART_2_REAL_DANY("6",2,"Dany", InputType.REAL, 27563421L),

    DAY_7_PART_1_EXAMPLE("7",1,null, InputType.EXAMPLE, 6440L),
    DAY_7_PART_2_EXAMPLE("7",2,null, InputType.EXAMPLE, 5905L),
    DAY_7_PART_1_REAL_ANTON("7",1,"Anton", InputType.REAL, 247_815_719L),
    DAY_7_PART_2_REAL_ANTON("7",2,"Anton", InputType.REAL, 248_747_492L),
    DAY_7_PART_1_REAL_DANY("7",1,"Dany", InputType.REAL, 250_898_830L),
    DAY_7_PART_2_REAL_DANY("7",2,"Dany", InputType.REAL, 252_127_335L),

    DAY_8_PART_1_EXAMPLE("8",1,null, InputType.EXAMPLE, 6),
    DAY_8_PART_2_EXAMPLE("8", "2", 2,null, InputType.EXAMPLE, BigInteger.valueOf(6)),
    DAY_8_PART_1_REAL_ANTON("8",1,"Anton", InputType.REAL, 18157),
    DAY_8_PART_2_REAL_ANTON("8",2,"Anton", InputType.REAL, new BigInteger("14299763833181")),
    DAY_8_PART_1_REAL_DANY("8",1,"Dany", InputType.REAL, 21251),
    DAY_8_PART_2_REAL_DANY("8",2,"Dany", InputType.REAL, new BigInteger("11678319315857")),

    DAY_9_PART_1_EXAMPLE("9",1,null, InputType.EXAMPLE, 114),
    DAY_9_PART_2_EXAMPLE("9",2,null, InputType.EXAMPLE, 2),
    DAY_9_PART_1_REAL_ANTON("9",1,"Anton", InputType.REAL, 1_819_125_966),
    DAY_9_PART_2_REAL_ANTON("9",2,"Anton", InputType.REAL, 1140),
    DAY_9_PART_1_REAL_DANY("9",1,"Dany", InputType.REAL, 1_731_106_378),
    DAY_9_PART_2_REAL_DANY("9",2,"Dany", InputType.REAL, 1087),

    DAY_10_PART_1_EXAMPLE("10",1,null, InputType.EXAMPLE, 8),
    DAY_10_PART_2_1_EXAMPLE("10","2-1",2,null, InputType.EXAMPLE, 4),
    DAY_10_PART_2_2_EXAMPLE("10","2-2",2,null, InputType.EXAMPLE, 4),
    DAY_10_PART_2_3_EXAMPLE("10","2-3",2,null, InputType.EXAMPLE, 8),
    DAY_10_PART_2_4_EXAMPLE("10","2-4",2,null, InputType.EXAMPLE, 10),
    DAY_10_PART_1_REAL_ANTON("10",1,"Anton", InputType.REAL, 7086),
    DAY_10_PART_2_REAL_ANTON("10",2,"Anton", InputType.REAL, 317),
    DAY_10_PART_1_REAL_DANY("10",1,"Dany", InputType.REAL, 7097),
    DAY_10_PART_2_REAL_DANY("10",2,"Dany", InputType.REAL, 355),

    DAY_11_PART_1_EXAMPLE("11",1,null, InputType.EXAMPLE, 374L),
    DAY_11_PART_2_EXAMPLE("11",2,null, InputType.EXAMPLE, 100, new BigInteger("8410")),
    DAY_11_PART_1_REAL_ANTON("11",1,"Anton", InputType.REAL, 10_154_062L),
    DAY_11_PART_2_REAL_ANTON("11",2,"Anton", InputType.REAL, 1_000_000, new BigInteger("553083047914")),
    DAY_11_PART_1_REAL_DANY("11",1,"Dany", InputType.REAL, 10289334L),
    DAY_11_PART_2_REAL_DANY("11",2,"Dany", InputType.REAL, 649862989626L),

    DAY_12_PART_1_EXAMPLE("12",1,null, InputType.EXAMPLE, 21L),
    DAY_12_PART_2_EXAMPLE("12",2,null, InputType.EXAMPLE, 525152L),
    DAY_12_PART_1_REAL_ANTON("12",1,"Anton", InputType.REAL, 7843L),
    DAY_12_PART_2_REAL_ANTON("12",2,"Anton", InputType.REAL, 10_153_896_718_999L),
//    DAY_12_PART_1_REAL_DANY("12",1,"Dany", InputType.REAL, -1),
//    DAY_12_PART_2_REAL_DANY("12",2,"Dany", InputType.REAL, -1),

    DAY_13_PART_1_EXAMPLE("13",1,null, InputType.EXAMPLE, 405),
    DAY_13_PART_2_EXAMPLE("13",2,null, InputType.EXAMPLE, 400),
    DAY_13_PART_1_REAL_ANTON("13",1,"Anton", InputType.REAL, 27202),
    DAY_13_PART_2_REAL_ANTON("13",2,"Anton", InputType.REAL, 41566),
    DAY_13_PART_1_REAL_DANY("13",1,"Dany", InputType.REAL, 27300),
    DAY_13_PART_2_REAL_DANY("13",2,"Dany", InputType.REAL, 29276),

    DAY_14_PART_1_EXAMPLE("14",1,null, InputType.EXAMPLE, 136),
    DAY_14_PART_2_EXAMPLE("14",2,null, InputType.EXAMPLE, 64),
    DAY_14_PART_1_REAL_ANTON("14",1,"Anton", InputType.REAL, 110677),
    DAY_14_PART_2_REAL_ANTON("14",2,"Anton", InputType.REAL, 90551),
    DAY_14_PART_1_REAL_DANY("14",1,"Dany", InputType.REAL, 109665),
    DAY_14_PART_2_REAL_DANY("14",2,"Dany", InputType.REAL, 96061),

    DAY_15_PART_1_EXAMPLE("15",1,null, InputType.EXAMPLE, 1320),
    DAY_15_PART_2_EXAMPLE("15",2,null, InputType.EXAMPLE, 145),
    DAY_15_PART_1_REAL_ANTON("15",1,"Anton", InputType.REAL, 506269),
    DAY_15_PART_2_REAL_ANTON("15",2,"Anton", InputType.REAL, 264021),
    DAY_15_PART_1_REAL_DANY("15",1,"Dany", InputType.REAL, 506891),
    DAY_15_PART_2_REAL_DANY("15",2,"Dany", InputType.REAL, 230462),

    DAY_16_PART_1_EXAMPLE("16",1,null, InputType.EXAMPLE, 46L),
    DAY_16_PART_2_EXAMPLE("16",2,null, InputType.EXAMPLE, 51L),
    DAY_16_PART_1_REAL_ANTON("16",1,"Anton", InputType.REAL, 7210L),
    DAY_16_PART_2_REAL_ANTON("16",2,"Anton", InputType.REAL, 7673L),
//    DAY_16_PART_1_REAL_DANY("16",1,"Dany", InputType.REAL, 506891),
//    DAY_16_PART_2_REAL_DANY("16",2,"Dany", InputType.REAL, 230462),

    DAY_17_PART_1_EXAMPLE("17",1,null, InputType.EXAMPLE, 102),
    DAY_17_PART_2_EXAMPLE("17",2,null, InputType.EXAMPLE, 94),
    DAY_17_PART_1_REAL_ANTON("17",1,"Anton", InputType.REAL, 771),
    DAY_17_PART_2_REAL_ANTON("17",2,"Anton", InputType.REAL, 930),
//    DAY_17_PART_1_REAL_DANY("17",1,"Dany", InputType.REAL, 506891),
//    DAY_17_PART_2_REAL_DANY("17",2,"Dany", InputType.REAL, 230462),

    DAY_18_PART_1_EXAMPLE("18",1,null, InputType.EXAMPLE, 62),
    DAY_18_PART_2_EXAMPLE("18",2,null, InputType.EXAMPLE, 952_408_144_115L),
    DAY_18_PART_1_REAL_ANTON("18",1,"Anton", InputType.REAL, 42317),
    DAY_18_PART_2_REAL_ANTON("18",2,"Anton", InputType.REAL, 83_605_563_360_288L),
//    DAY_18_PART_1_REAL_DANY("18",1,"Dany", InputType.REAL, 506891),
//    DAY_18_PART_2_REAL_DANY("18",2,"Dany", InputType.REAL, 230462),

    DAY_19_PART_1_EXAMPLE("19",1,null, InputType.EXAMPLE, 19114L),
    DAY_19_PART_2_EXAMPLE("19",2,null, InputType.EXAMPLE, 167409079868000L),
    DAY_19_PART_1_REAL_ANTON("19",1,"Anton", InputType.REAL, 374873L),
    DAY_19_PART_2_REAL_ANTON("19",2,"Anton", InputType.REAL, 122112157518711L),

    DAY_20_PART_1_1_EXAMPLE("20", "1",1,null, InputType.EXAMPLE, 32_000_000L),
    DAY_20_PART_1_2_EXAMPLE("20", "2", 1,null, InputType.EXAMPLE, 11_687_500L),
    DAY_20_PART_1_REAL_ANTON("20",1,"Anton", InputType.REAL, 841_763_884L),
    DAY_20_PART_2_REAL_ANTON("20",2,"Anton", InputType.REAL, 246_006_621_493_687L),

    DAY_21_PART_1_EXAMPLE("21", 1,null, InputType.EXAMPLE, 6, 16L),
    DAY_21_PART_1_REAL_ANTON("21",1,"Anton", InputType.REAL, 64, 3649L),
    DAY_21_PART_2_REAL_ANTON("21",2,"Anton", InputType.REAL, 26_501_365, 612_941_134_797_232L),

    DAY_22_PART_1_EXAMPLE("22", 1,null, InputType.EXAMPLE, 5L),
    DAY_22_PART_2_EXAMPLE("22",  1,null, InputType.EXAMPLE, 7L),
    DAY_22_PART_1_REAL_ANTON("22",1,"Anton", InputType.REAL, 471L),
    DAY_22_PART_2_REAL_ANTON("22",2,"Anton", InputType.REAL, -1),
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

    InputAnswer(String day, int part, String user, InputType inputType, Number additionalInput, Number answer) {
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
        res.append("day").append(day).append("/").append(inputType.namePattern).append(" ").append(day);
        if (subInputNum != null) {
            res.append("-").append(subInputNum);
        }
        if (inputType.equals(InputType.REAL)) {
            res.append(" ").append(user);
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
