package collections;

import collections.CollectionsMethods.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDemo {
    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Ace of Hearts", 1);
        System.out.println("-".repeat(50));
        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size: " + cards.size());
//        System.out.println("-".repeat(50));
        List<Card> acesHearts = Collections.nCopies(13, aceOfHearts);
        System.out.println(acesHearts.size());
        Card.printDeck(acesHearts, "Aces of Hearts", 1);
//        System.out.println("-".repeat(50));
        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsClubs, "Kings of Clubs", 1);
//        System.out.println("-".repeat(50));

//        Collections.addAll(cards, cardArray);
//        Collections.addAll(cards, cardArray);
//        Card.printDeck(cards, " Card Collection with Aces added", 1);
//        Collections.copy(cards, kingsClubs);
//        Card.printDeck(cards, "Cards list at start", 2);
        System.out.println(cards.size());

        cards = List.copyOf(deck);
        Card.printDeck(cards, "Cards list at start reassigned", 4);
        System.out.println(cards.size());

    }
}
