import java.util.List;

/**
 * BlackJackDealer
 * A black jack dealer class extends the Abstract Player class, implement Dealer interface, contains the dealer logic, e.g keep drawing card when the score is less than 17
 */
public class BlackJackDealer extends AbstractBlackJackPlayer implements Dealer {

    public BlackJackDealer() {
        super("Black Jack Dealer");
    }

    @Override
    public void printCard() {
        Hand hand = hands.get(0);
        System.out.println(getName() + " card :");
        hand.printCard();
    }

    @Override
    public void printResult() {
        System.out.println(getName() + ": Balance = " + balance.getBalance());
    }


    /**
     * Dealer only has one hand, get the score
     * @return
     */
    public Integer score() {
        Integer score = hands.get(0).score();
        return score;
    }

    /**
     * print the card in hand
     */
    public void printDealerCard() {
        Hand hand = hands.get(0);
        System.out.println(getName() + " card :");
        List<Card> cards = hand.getCards();
        System.out.println("[" + cards.get(0) + "，**]");
    }

    /**
     * keep drawing card when the score is less than 17
     * @return
     */
    public boolean isOver() {
        Integer score = score();
        if(score > 17){
            return true;
        }
        hands.get(0).hit();
        return false;
    }
}
