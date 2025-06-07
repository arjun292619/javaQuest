package collections;

import collections.CollectionsMethods.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsShuffleReverseSublist {
    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);
//        Shuffle the Deck
        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck", 4);
//        Reverse the deck
        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck", 4);
//        Sort the deck
        Comparator<Card> cardComparator1 = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        Collections.sort(deck, cardComparator1);
//        deck.sort(null);
        Card.printDeck(deck, "Standard Deck sorted by rank, suit", 13);
        Collections.reverse(deck);
        Card.printDeck(deck, "Standard Deck reverse sorted by rank, suit", 13);

        List<Card> kings = new ArrayList<>(deck.subList(4, 8));
        Card.printDeck(kings, "Kings in deck", 1);

        List<Card> tens = new ArrayList<>(deck.subList(16, 20));
        Card.printDeck(tens, "Tens in deck", 1);

        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("-".repeat(50));
        Card.printDeck(deck, "Current Deck", 4);
        System.out.println("sublist index for Tens: " + subListIndex);
        System.out.println("Contains = " + deck.containsAll(tens));
        Collections.shuffle(deck);
        System.out.println("-".repeat(50));
        System.out.println("Check after shuffling");
        Card.printDeck(deck, "Shuffled Deck", 4);
        int subListIndex1 = Collections.indexOfSubList(deck, tens);
        System.out.println("sublist index for Tens: " + subListIndex1);
        System.out.println("Contains = " + deck.containsAll(tens));
        System.out.println("-".repeat(50));
        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("Tens & deck set is disjointed? " + disjoint);
        boolean disjoint1 = Collections.disjoint(kings, tens);
        System.out.println("Tens & kings set is disjointed? " + disjoint1);

    }
}
