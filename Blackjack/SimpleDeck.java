/**
 * deck - exclude jokers
 */
public class SimpleDeck extends Deck{

    @Override
    protected void initDeck() {
        deck = initSimpleDeck();
    }
}
