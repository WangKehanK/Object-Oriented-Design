import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * A black jack game class, implement Game interface, combine deck, hand, balance, player class to create black jack game, allow multiple players in one game by modify playerNumber parameter
 */
public class BlackJackGame implements Game {
    private int playerNumber;
    private List<BlackJackPlayer> players;
    private BlackJackDealer dealer;

    private Deck deck;

    public BlackJackGame() {
        //If multi-player mode, ask player to enter player number
        System.out.println("please input player number:");
        Integer playerNumber =  InputUtil.getInputInteger();
        while(playerNumber == null){
            System.out.println("input number error");
            System.out.println("please input player number:");
            playerNumber = InputUtil.getInputInteger();
        }
        this.playerNumber = playerNumber;
    }

    public BlackJackGame(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public void initGame() {
        this.players = new ArrayList<>();
        for(int i = 0; i < playerNumber; i++){
            System.out.println("please input player No." + (i + 1) + " name:");
            String playerName = InputUtil.getInput();
            players.add(new BlackJackPlayer(playerName));
        }
        dealer = new BlackJackDealer();
        // How many decks, 2 means play with 2 decks
        deck = new MultipleDeck(2);
    }

    @Override
    public void startGame() {
        while(true){
            //start shuffle
            deck.shuffle();

            //player bet
            for(BlackJackPlayer player : players){
                if(!player.isGameOver()){
                    player.addGame();
                    // how many left in balance
                    player.printBalance();
                    // bet, how much?
                    player.bet();
                }
            }

            //Dealer get card, and check the winning status
            startHand(dealer);
            dealer.printDealerCard();
            for(BlackJackPlayer player : players){
                if(!player.isGameOver()){
                    startHand(player);
                    System.out.println();
                }
            }


            for(BlackJackPlayer blackJackPlayer : players){
                if(!blackJackPlayer.isGameOver()){
                    blackJackPlayer.printCard();
                    chooseOperate(blackJackPlayer);
                }
            }

            dealer.printCard();
            while(!dealer.isOver()){
                dealCard(dealer);
                dealer.printCard();
            }

            // final result
            checkResult();

            // continue or not
            for(BlackJackPlayer blackJackPlayer : players){
                if(!blackJackPlayer.isGameOver()){
                    blackJackPlayer.printBalance();
                    System.out.println("Player "+ blackJackPlayer.getName() +" Continue? (yes/no)");
                    boolean isContinue= blackJackPlayer.isContinue();
                    if(!isContinue){
                        blackJackPlayer.printResult();
                    }
                }
            }

            // If all player exit, this game is over, back to game list stage
            if(getPlayerNumber() == 0){
                System.out.println("GAME OVER!");
                print();
                break;
            }
        }
    }


    private int getPlayerNumber() {
        int count = 0;
        for(BlackJackPlayer player : players){
            if(!player.isGameOver()){
                count++;
            }
        }
        return count;
    }

    private void dealCard(AbstractBlackJackPlayer player) {
        for(Hand hand : player.getHands()){
            if(hand.needDeal()){
                hand.dealCard(deck.getNextCard());
                if(hand.isOver()){
                    System.out.println("hand is over.");
                    hand.printCard();
                }
            }
        }
    }

    private void chooseOperate(BlackJackPlayer blackJackPlayer) {
        String options = "0 hit, 1 stand";
        Queue<Hand> handQueue = new LinkedList<>(blackJackPlayer.getHands());
        while(!handQueue.isEmpty()){
            Hand hand = handQueue.poll();
            while(!hand.isOver()){
                hand.printCard();
                if(hand.isFirstRound()){
                    if(hand.canSplit()){
                        System.out.println(options + ", 2 double, 3 split");
                    }else{
                        System.out.println(options + ", 2 double");
                    }
                }else{
                    System.out.println(options);
                }
                Hand chooseResult = blackJackPlayer.choose(hand);
                if(chooseResult != null){
                    handQueue.add(chooseResult);
                    if(chooseResult.needDeal()){
                        chooseResult.dealCard(deck.getNextCard());
                    }
                }
                if(hand.needDeal()){
                    hand.dealCard(deck.getNextCard());
                }
            }
            hand.printCard();
            System.out.println();
        }

    }

    private void startHand(AbstractBlackJackPlayer player) {
        for(Hand hand : player.getHands()){
            hand.dealCard(deck.getNextCard());
            hand.dealCard(deck.getNextCard());
            if(hand.checkBlackJack() && !(player instanceof BlackJackDealer)){
                hand.printCard();
                System.out.println("Black Jack！");
            }
        }
    }

    private void checkResult() {
        Integer dealerScore = dealer.score();
        for(BlackJackPlayer blackJackPlayer : players){
            if(!blackJackPlayer.isGameOver()){
                List<Hand> hands = blackJackPlayer.getHands();
                for(Hand hand: hands){
                    Integer score = hand.score();
                    if(score > 21 || (dealerScore <= 21 && dealerScore > score)){
                        blackJackPlayer.fail(hand);
                        dealer.win(hand);
                    }else if(dealerScore <= 21 && score <= 21 && dealerScore.equals(score)){
                        blackJackPlayer.peace(hand);
                        dealer.peace(hand);
                    }else {
                        blackJackPlayer.win(hand);
                        dealer.fail(hand);
                    }
                }
                blackJackPlayer.initHand();
                dealer.initHand();
            }
        }
    }

    @Override
    public void print() {
        System.out.println("Player:");

        for(BlackJackPlayer player: players){
            player.printResult();
        }

        System.out.println("Dealer:");
        dealer.printResult();
        System.out.println();
    }
}
