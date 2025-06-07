package collections.CollectionsMethods;

import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, int rank) {
    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return new char[]{9827, 9830, 9829, 9824}[this.ordinal()];
        }
    }

    @Override
    public String toString() {
        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
    }

    public static Card getNumericCard(Suit suit, int cardNumber) {
        if (cardNumber > 1 && cardNumber < 11) {
            return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
        } else {
            throw new IllegalArgumentException("Incorrect arguemnts given. Please check the input");
        }
    }

    public static Card getFaceCard(Suit suit, char c) {
//        boolean isFace = "JQKA".contains(String.valueOf(c));
        int charIndex = "JQKA".indexOf(c);
        if (charIndex > -1) {
            return new Card(suit, "" + c, 9 + charIndex);
        } else {
            throw new IllegalArgumentException("Incorrect arguemnts given. Please check the input");
        }
    }

    public static List<Card> getStandardDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(s, i));
            }
            for (char c : "JQKA".toCharArray()) {
                deck.add(getFaceCard(s, c));
            }
        }
        return deck;
    }

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 4);
    }

    public static void printDeck(List<Card> deck, String description, int rows) {
        System.out.println("-".repeat(50));
        if (description != null) {
            System.out.println(description);
        }
        int cardsColumn = deck.size() / rows;
        for (int i = 0; i < rows; i++) {
            int start = i * cardsColumn;
            int end = start + cardsColumn;
            deck.subList(start, end).forEach(card -> System.out.print(card + " "));
            System.out.println();
        }
    }



}
