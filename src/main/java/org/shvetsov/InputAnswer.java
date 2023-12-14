package org.shvetsov;

import lombok.Getter;

@SuppressWarnings("UNUSED")
public enum InputAnswer {
    DAY_6_PART_1_EXAMPLE(6,1,null, InputType.EXAMPLE, 288),
    DAY_6_PART_2_EXAMPLE(6,2,null, InputType.EXAMPLE, 71503L),
    DAY_6_PART_1_REAL_ANTON(6,1,"Anton", InputType.REAL, 503424),
    DAY_6_PART_2_REAL_ANTON(6,2,"Anton", InputType.REAL, 32607562L),
    DAY_6_PART_1_REAL_DANY(6,1,"Dany", InputType.REAL, 74698),
    DAY_6_PART_2_REAL_DANY(6,2,"Dany", InputType.REAL, 27563421L);

    private static final String RESOURCES = "src/main/resources/";

    private final int day;
    private final int part;
    private final String user;
    private final InputType inputType;
    @Getter
    private final Number answer;

    InputAnswer(int day, int part, String user, InputType inputType, Number answer) {
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
