package org.shvetsov.day4;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Elf {

    private final int start;
    private final int end;

    public Elf(String elf) {
        this.start = Integer.parseInt(StringUtils.substringBefore(elf, "-"));
        this.end = Integer.parseInt(StringUtils.substringAfter(elf, "-"));
    }

    public boolean contains(Elf other) {
        return other.start >= this.start && other.end <= this.end;
    }

    public boolean overlaps(Elf other) {
        return !(other.end < this.start || other.start > this.end);
    }
}
