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
    DAY_8_PART_2_EXAMPLE("8-2",2,null, InputType.EXAMPLE, BigInteger.valueOf(6)),
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
    ;

    private static final String RESOURCES = "src/main/resources/";

    private final String day;
    private final int part;
    private final String user;
    private final InputType inputType;
    @Getter
    private final Number answer;

    InputAnswer(String day, int part, String user, InputType inputType, Number answer) {
        this.day = day;
        this.part = part;
        this.user = user;
        this.inputType = inputType;
        this.answer = answer;
    }

    public String getInput(InputFormat inputFormat) {
        StringBuilder res = new StringBuilder();
        if (inputFormat.equals(InputFormat.PATH)) {
            res.append(RESOURCES);
        }
        res.append(inputType.namePattern).append(" ").append(day);
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
