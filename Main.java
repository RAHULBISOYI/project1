



import java.util.*;

enum Suit {
    HEARTS(1), SPADES(3), DIAMONDS(2), CLUBS(4);

    private final int value;

    Suit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("Deck is empty");
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();

        // Shuffle the deck before drawing cards
        deck.shuffle();

        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            drawnCards.add(deck.drawCard());
        }

        // Sort using various Comparators
        Comparator<Card> cardComparator_1 = Comparator.comparing((Card card) -> card.getSuit().ordinal() % 2)
                .thenComparing(Card::getSuit, Comparator.naturalOrder())
                .thenComparing(Card::getRank, Comparator.naturalOrder());

        Comparator<Card> cardComparator_2 = Comparator.comparing((Card card) -> card.getSuit().ordinal() % 2)
                .thenComparing(Card::getSuit).thenComparing(Card::getRank);

        Comparator<Card> cardComparator_3 = Comparator.comparing((Card card) -> card.getSuit().ordinal() % 2)
                .thenComparing(card -> card.getSuit().ordinal(), Comparator.naturalOrder())
                .thenComparing(card -> card.getRank().getValue());

        Comparator<Card> cardComparator_4 = Comparator.comparing((Card card) -> card.getSuit().getValue())
                .thenComparing(card -> card.getRank().getValue());

        Collections.sort(drawnCards, cardComparator_4);

        System.out.println("Drawn cards (sorted):");
        for (Card card : drawnCards) {
            System.out.println(card);
        }
    }
}
