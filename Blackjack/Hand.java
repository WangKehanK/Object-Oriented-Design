import java.util.ArrayList;
import java.util.List;

/**
 * A hand class, record what card does a player have in their hand, calculate the total score of card, handle A card(can be 1 or 11)
 */
public class Hand {
    private int bet;
    private List<Card> cards;

    //hit or not
    private boolean deal;
    //stand or not
    private boolean stand;
    // > 21
    private boolean over;

    // = 21
    private boolean blackJack;


    public Hand() {
        cards = new ArrayList<>();
        bet = 0;
        deal = false;
        stand = false;
        over = false;
        blackJack = false;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    /**
     * Calculate score, A can be 1 or 11
     * @return
     */
    public Integer score() {
        List<Card> aceCards = new ArrayList<>();
        int score = 0;
        for (Card card : cards) {
            if (card.getValue().equals(CardValueType.JACK) ||
                    card.getValue().equals(CardValueType.QUEEN) ||
                    card.getValue().equals(CardValueType.KING)) {
                score += 10;
            } else if (card.getValue().equals(CardValueType.ACE)) {
                aceCards.add(card);
            } else {
                score += Integer.parseInt(card.getValue().getShortName());
            }
        }
        if (!aceCards.isEmpty()) {
            if (score + aceCards.size() > 11) {
                score += aceCards.size();
            } else {
                score += 11;
                score += (aceCards.size() - 1);
            }
        }
        return score;
    }

    /**
     * print the card in hand
     */
    public void printCard() {
        String print = "[";
        for (Card card : cards) {
            print += card.toString();
            print += ",";
        }

        print = print.substring(0, print.length() - 1);
        print += "]";
        print += " Score = ";
        print += score();

        System.out.println(print);
    }

    /**
     * check whether the card can be splited
     * @return
     */
    public boolean canSplit() {
        if (cards.size() == 2 && cards.get(0).getValue().equals(cards.get(1).getValue())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check whether the card is black jack
     * @return
     */
    public boolean isBlackJack() {
        return blackJack;
    }

    /**
     * check the number of round
     * @return
     */
    public boolean isFirstRound() {
        return cards.size() == 2;
    }

    /**
     * hit
     */
    public void hit() {
        deal = true;
    }

    public void stand() {
        stand = true;
    }

    public void doubleUp() {
        setBet(getBet() * 2);
    }

    public Hand split() {
        Hand hand = new Hand();
        hand.dealCard(cards.get(1));
        cards.remove(1);
        hand.setBet(getBet());

        deal = true;
        hand.deal = true;

        return hand;
    }

    /**
     * hit or not
     * @return
     */
    public boolean needDeal() {
        return deal;
    }

    /**
     * add a card
     */
    public void dealCard(Card card) {
        cards.add(card);
        int score = score();
        if(score >= 21){
            over = true;
        }
        deal = false;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * is over?
     */
    public boolean isOver() {
        return over || stand;
    }

    public void printBet() {
        System.out.println("Now bet = " + bet);
    }

    /**
     * First round back jack
     */
    public boolean checkBlackJack() {
        if(cards.size() == 2 && score() == 21){
            blackJack = true;
            over = true;
            return true;
        }else{
            return false;
        }
    }
}
