package org.shvetsov.day2;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

@Data
public class Game {

    private int gameNumber;
    private List<Reveal> reveals;

    public Game(String gameString) {
        this.gameNumber = Integer.parseInt(StringUtils.substringBetween(gameString, "Game ", ":"));
        String revealsString = StringUtils.substringAfter(gameString, ":");
        this.reveals = Arrays.stream(StringUtils.split(revealsString, ";"))
                .map(Reveal::new)
                .toList();
    }

    public boolean isValid(int maxRed, int maxGreen, int maxBlue) {
        for (Reveal reveal :
                reveals) {
            if (reveal.red > maxRed) return false;
            if (reveal.green > maxGreen) return false;
            if (reveal.blue > maxBlue) return false;
        }
        return true;
    }

    public int getPower() {
        int minRedNeeded = 0;
        int minGreenNeeded = 0;
        int minBlueNeeded = 0;
        for (Reveal reveal :
                reveals) {
            minRedNeeded = Math.max(reveal.red, minRedNeeded);
            minGreenNeeded = Math.max(reveal.green, minGreenNeeded);
            minBlueNeeded = Math.max(reveal.blue, minBlueNeeded);
        }
        return minRedNeeded * minGreenNeeded * minBlueNeeded;
    }

    public record Reveal(int red, int green, int blue) {
        public Reveal(String revealString) {
            this(revealString.contains("red")
                            ? Integer.parseInt(StringUtils.substringAfterLast(StringUtils.substringBefore(revealString, " red"), " "))
                            : 0
                    , revealString.contains("green")
                            ? Integer.parseInt(StringUtils.substringAfterLast(StringUtils.substringBefore(revealString, " green"), " "))
                            : 0
                    , revealString.contains("blue")
                            ? Integer.parseInt(StringUtils.substringAfterLast(StringUtils.substringBefore(revealString, " blue"), " "))
                            : 0);
        }
    }
}
