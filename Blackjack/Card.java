/**
 * A public class use CardValue Type and CardSuitType to build a deck of poker
 */
public class Card {

    private CardSuitType suit;
    private CardValueType value;


    public Card(CardSuitType suit, CardValueType value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        if(suit == null){
            return value.getShortName();
        }else{
            return suit.getSuitType() + value.getShortName();
        }
    }

    public CardSuitType getSuit() {
        return suit;
    }

    public CardValueType getValue() {
        return value;
    }
}
