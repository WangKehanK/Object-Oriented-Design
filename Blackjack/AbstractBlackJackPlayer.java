import java.util.ArrayList;
import java.util.List;
/**
 * AbstractBlackJackPlayer - A abstract player class implement player interface, use Hand, Balance class to build the basic logic, like check the game status of player
 */
public abstract class AbstractBlackJackPlayer implements Player {
    private String name;
    protected List<Hand> hands;
    protected Balance balance;


    public AbstractBlackJackPlayer(String name) {
        this.name = name;
        balance = new Balance();
        hands = new ArrayList<>();
        hands.add(new Hand());
    }

    public String getName() {
        return name;
    }

    /**
     * Wining this hand, add money to balance
     * @param hand
     */
    public void win(Hand hand) {
        if (hand.isBlackJack()) {
            balance.add(hand.getBet() * 1.5);
            System.out.println(name + " win Hand. balance + " + (hand.getBet() * 1.5));
        } else {
            balance.add(hand.getBet());
            System.out.println(name + " win Hand. balance + " + hand.getBet());
        }
    }

    /**
     * Lose this hand, reduce money from balance
     * @param hand
     */
    public void fail(Hand hand) {
        if (hand.isBlackJack()) {
            balance.reduce(hand.getBet() * 1.5);
            System.out.println(name + " lose Hand. balance - " + (hand.getBet() * 1.5));
        } else {
            balance.reduce(hand.getBet());
            System.out.println(name + " lose Hand. balance - " + hand.getBet());
        }
    }

    /**
     *  This is the condition when game is tied (peace game)
     */
    public void peace(Hand hand) {
        System.out.println(name + " Hand peace.");
    }

    /**
     *  reset the hand(empty hand, no card)
     */
    public void initHand() {
        hands = new ArrayList<>();
        hands.add(new Hand());
    }

    public List<Hand> getHands() {
        return hands;
    }

    public abstract void printCard();

    public abstract void printResult();

}
