import java.util.ArrayList;

public class MultipleDeck extends Deck{
    private int deckNumber;

    public MultipleDeck(int deckNumber) {
        this.deckNumber = deckNumber;
    }

    @Override
    protected void initDeck() {
        deck = new ArrayList<>();
        for(int i = 0; i < deckNumber; i++){
            deck.addAll(initSimpleDeck());
        }
    }
}
