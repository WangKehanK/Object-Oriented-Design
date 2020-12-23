/**
 * a deck with joker
 */
public class AllDeck extends Deck{

    @Override
    protected void initDeck() {
        deck = initSimpleDeck();

        deck.add(new Card(null, CardValueType.BLACK_JOKER));
        deck.add(new Card(null, CardValueType.RED_JOKER));
    }
}
