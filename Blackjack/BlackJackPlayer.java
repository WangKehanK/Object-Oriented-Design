/**
 * BlackJackPlayer - A black jack player class extends the Abstract Player class, contains functions that help printing bet, print cards, ask user whether continue, give game options(stand, hit)
 */
public class BlackJackPlayer extends AbstractBlackJackPlayer {

    public final static int HIT = 0;
    public final static int STAND = 1;
    public final static int DOUBLE = 2;
    public final static int SPLIT = 3;

    //records win numbers
    private int gameNumber;
    private int winNumber;

    //check whether game is over
    private boolean gameOver;

    public BlackJackPlayer(String playerName, int initBalance) {
        super(playerName);
        this.balance = new Balance(initBalance);
        gameNumber = 0;
        winNumber = 0;
        gameOver = false;
    }

    public BlackJackPlayer(String playerName) {
        this(playerName, 1000);
    }


    /**
     * print the amount of balance
     */
    public void printBalance() {
        System.out.println("Player " + getName() + " balance = " + balance.getBalance());
    }

    /**
     * get the number of bet
     */
    public void bet() {
        int index = 1;
        for (Hand hand : hands) {
            System.out.println("please input Hand No." + index + " bet number.");
            Integer betNumber = InputUtil.getInputInteger();
            while (betNumber == null || betNumber > balance.getBalance()) {
                System.out.println("please input Hand No." + index + " bet number.");
                betNumber = InputUtil.getInputInteger();
            }
            hand.setBet(betNumber);
            index++;
        }
    }

    @Override
    /**
     * print card (in hand) information
     */
    public void printCard() {
        int index = 1;
        for (Hand hand : hands) {
            System.out.println(getName() + " Hand No." + index + " card :");
            hand.printCard();
            hand.printBet();
            System.out.println();
            index++;
        }
    }

    /**
     * ask for input
     * @param hand
     * @return
     */
    public Hand choose(Hand hand) {
        Integer option = InputUtil.getInputInteger();
        //error handling
        while (option == null || (option != HIT && option != STAND && option != DOUBLE && option != SPLIT)
                || ((option == DOUBLE || option == SPLIT) && balance.getBalance() < (hand.getBet() * 2) )
                || (option == DOUBLE && !hand.isFirstRound()) || (option == SPLIT && !hand.canSplit())) {
            if (option == null) {
                System.out.println("Input must be number. Try again");
            } else if (option != HIT && option != STAND && option != DOUBLE && option != SPLIT) {
                System.out.println("Input Error. Try again");
            } else {
                System.out.println("Balance less than hand's bet");
            }
            option = InputUtil.getInputInteger();
        }

        //what user choose
        switch (option) {
            case HIT:
                hand.hit();
                break;
            case STAND:
                hand.stand();
                break;
            case DOUBLE:
                hand.doubleUp();
                System.out.println("Now bet = " + hand.getBet());
                break;
            case SPLIT:
                //split
                Hand newHand = hand.split();
                hands.add(newHand);
                return newHand;
            default:
                break;
        }
        return null;
    }

    /**
     * whether continue
     * @return
     */
    public boolean isContinue() {
        String input = InputUtil.getInput();
        while(true){
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
                return true;
            }else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
                gameOver = true;
                return false;
            }else{
                System.out.println("input error.try again");
                input = InputUtil.getInput();
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Player " + getName() + ": Balance = " + balance.getBalance() + ",Game Number = " + gameNumber + ",Win Number = " + winNumber);
    }

    @Override
    public void win(Hand hand) {
        super.win(hand);
        winNumber++;
    }

    @Override
    public void fail(Hand hand) {
        super.fail(hand);
        if(balance.isEmpty()){
            System.out.println(getName() + " Balance = 0. Game over");
            gameOver = true;
        }
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void addGame() {
        this.gameNumber++;
    }
}
