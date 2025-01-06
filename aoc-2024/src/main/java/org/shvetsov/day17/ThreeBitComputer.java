package org.shvetsov.day17;

import org.shvetsov.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeBitComputer {

    public long a;
    public long b;
    public long c;
    public int[] program;
    public List<Integer> output = new ArrayList<>();

    public ThreeBitComputer(long a, long b, long c, String program) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.program = Arrays.stream(program.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public String getOutput() {
        return output.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public void run() {
        int i = 0;
        while (Utils.isIndexExistInArray(program, i)) {
            i = operate(program[i], program[i + 1], i);
        }
    }

    private int operate(int opcode, int operand, int pointer) {
        switch (opcode) {
            case 0 -> adv(operand);
            case 1 -> bxl(operand);
            case 2 -> bst(operand);
            case 3 -> {
                int jnz = jnz(operand);
                return jnz >= 0 ? jnz : pointer + 2;
            }
            case 4 -> bxc();
            case 5 -> out(operand);
            case 6 -> bdv(operand);
            case 7 -> cdv(operand);
            default -> throw new IllegalStateException("Unknown instruction, opcode: " + opcode);
        }
        return pointer + 2;
    }

    private void adv(int operand) {
        long numerator = this.a;
        long denominator = 1L << getComboOperand(operand);
        this.a = numerator / denominator;
    }

    private void bxl(int operand) {
        this.b = this.b ^ operand;
    }

    private void bst(int operand) {
        this.b = getComboOperand(operand) % 8;
    }

    private int jnz(int operand) {
        if (this.a != 0) {
            return operand;
        } else {
            return -1;
        }
    }

    private void bxc() {
        this.b = this.b ^ this.c;
    }

    private void out(int operand) {
        this.output.add((int) (getComboOperand(operand) % 8));
    }

    private void bdv(int operand) {
        long numerator = this.a;
        long denominator = 1L << getComboOperand(operand);
        this.b = numerator / denominator;
    }

    private void cdv(int operand) {
        long numerator = this.a;
        long denominator = 1L << getComboOperand(operand);
        this.c = numerator / denominator;
    }


    private long getComboOperand(int operand) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> this.a;
            case 5 -> this.b;
            case 6 -> this.c;
            case 7 ->
                    throw new IllegalStateException("Combo operand 7 is reserved and will not appear in valid programs");
            default -> throw new IllegalStateException("Unexpected operand: " + operand);
        };
    }

}
