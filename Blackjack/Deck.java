import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A public class, represent decks of poker card, can be shuffled
 */
public abstract class Deck {
    protected List<Card> deck;

    public Deck() {
        initDeck();
    }

    /**
     * Shuffle the deck
     */
    public void shuffle() {
        initDeck();
        System.out.println();
        System.out.println("Shuffling");
        System.out.println();
        Collections.shuffle(deck);
    }

    /**
     * Get the next card
     * @return
     */
    public Card getNextCard() {
        if(deck.isEmpty()){
            shuffle();
        }
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    /**
     * init a deck of card without Joker
     * @return
     */
    public List<Card> initSimpleDeck(){
        List<Card> deck = new ArrayList<>();
        for(CardSuitType cardSuitType : CardSuitType.values()){
            for(CardValueType cardValueType : CardValueType.values()){
                if(cardValueType.equals(CardValueType.BLACK_JOKER) || cardValueType.equals(CardValueType.RED_JOKER)){
                    continue;
                }
                deck.add(new Card(cardSuitType, cardValueType));
            }
        }
        return deck;
    }

    /**
     * init a default deck of card
     */
    protected abstract void initDeck();
}
