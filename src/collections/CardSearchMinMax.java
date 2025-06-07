package collections;

import collections.CollectionsMethods.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardSearchMinMax {
    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();

        Comparator<Card> sortLogic = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        Collections.sort(deck, sortLogic);
        Card.printDeck(deck);

        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
//        Search the Collections
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortLogic);
        System.out.println("found Index: " + foundIndex);
        System.out.println("found Index through indexOf: " + deck.indexOf(tenOfHearts));
        System.out.println(deck.get(foundIndex));
//        Replace on Collections
        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
        Card.printDeck(deck.subList(32, 36), "Tens Row", 1);

        Collections.replaceAll(deck, tenOfHearts, tenOfClubs);
        Card.printDeck(deck.subList(32, 36), "Tens Row", 1);

        if (Collections.replaceAll(deck, tenOfHearts, tenOfClubs)) {
            System.out.println("Tens of Hearts were replaced");
        } else {
            System.out.println("Tens of Hearts were not replaced");
        }

//        Check the frequency of an occurence
        System.out.println("# Tens of Clubs: " + Collections.frequency(deck, tenOfClubs));

//        Min & Max of a Collection
        System.out.println("Best card of Collection: " + Collections.max(deck, sortLogic));
        System.out.println("Best card of Collection: " + Collections.min(deck, sortLogic));
//        instance method Sort
        Comparator<Card> sortBySuit = Comparator.comparing(Card::suit).thenComparing(Card::rank);
        deck.sort(sortBySuit);
        Card.printDeck(deck, "Sorted by Suit", 4);
//        Rotate the collection
        List<Card> partialList = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(partialList, 2);
        System.out.println("Unrotated Collection: " + deck.subList(0, 13));
        System.out.println("Rotated Collection: " + partialList);
        System.out.println("-".repeat(50));
        Collections.rotate(partialList, -4);
        System.out.println("Unrotated Collection: " + deck.subList(0, 13));
        System.out.println("Rotated Collection: " + partialList);
//        Swap elements in List
        partialList = new ArrayList<>(deck.subList(0, 13));
        for (int i = 0; i < partialList.size() / 2; i++) {
            Collections.swap(partialList, i, partialList.size() - 1 - i);
        }
        System.out.println("Manually reversed Array: " + partialList);
    }
}
