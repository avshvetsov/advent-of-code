package org.shvetsov.day4;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.shvetsov.Utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/4">Day 4</a>
 */
public class Day4 {

    public static final String INPUT_PATH = "src/main/resources/input 4 Anton.txt";
    public static final String INPUT_NAME = "input 4 Anton.txt";

    public static void main(String[] args) {
        List<String> input = Utils.parseInputByNewLine(INPUT_PATH);
        Day4 day = new Day4();
        System.out.println(day.partOneAnton(input));
        System.out.println(day.partTwoAnton(input));
    }

    public int partOneAnton(List<String> cardsString) {
        return cardsString.stream()
                .map(Card::new)
                .mapToInt(Card::countWinningPoint)
                .sum();
    }

    private final static Map<Integer, Card2> CARD_DECK = new HashMap<>();

    public int partTwoAnton(List<String> cardsString) {
        int cardsTotal = 0;
        for (int i = cardsString.size() - 1; i >= 0; i--) {
            Card2 card = new Card2(cardsString.get(i));
            CARD_DECK.put(card.getCardNumber(), card);
            cardsTotal += card.getTotalCards();
        }
        return cardsTotal;
    }

    @Data
    private class Card {
        private List<Integer> winingNumbers;
        private List<Integer> cardNumbers;

        public Card(String cardData) {
            this.winingNumbers = Arrays.stream(StringUtils.substringBetween(cardData.replaceAll(" +", " "), ": ", " |").split(" "))
                    .map(Integer::parseInt)
                    .toList();
            this.cardNumbers = Arrays.stream(StringUtils.substringAfter(cardData.replaceAll(" +", " "), "| ").split(" "))
                    .map(Integer::parseInt)
                    .toList();
        }

        public int countWinningPoint() {
            List<Integer> common = new ArrayList<>(cardNumbers);
            common.retainAll(winingNumbers);
            return common.isEmpty() ? 0 : 1 << (common.size() - 1);
        }
    }

    @Data
    private class Card2 {
        private final int cardNumber;
        private final int wonNumber;
        private final List<Card2> wonCards;
        private final int totalCards;

        public Card2(String cardData) {
            cardData = cardData.replaceAll(" +", " ");
            this.cardNumber = Integer.parseInt(StringUtils.substringBetween(cardData, "Card ", ":"));
            this.wonNumber = countWinningNumbers(cardData);
            this.wonCards = IntStream.range(cardNumber + 1, cardNumber + wonNumber + 1)
                    .filter(CARD_DECK::containsKey)
                    .mapToObj(CARD_DECK::get)
                    .toList();
            this.totalCards = countCardsTotal();
        }

        private int countWinningNumbers(String cardData) {
            List<Integer> winingNumbers = Arrays.stream(StringUtils.substringBetween(cardData, ": ", " |").split(" "))
                    .map(Integer::parseInt)
                    .toList();
            List<Integer> cardNumbers = Arrays.stream(StringUtils.substringAfter(cardData, "| ").split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            cardNumbers.retainAll(winingNumbers);
            return cardNumbers.size();
        }

        private int countCardsTotal() {
            int totalCardsWon = wonCards.stream()
                    .mapToInt(Card2::getTotalCards)
                    .sum();
            return totalCardsWon + 1;
        }
    }
}
