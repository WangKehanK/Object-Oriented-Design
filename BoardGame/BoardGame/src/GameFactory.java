import game.Game;
import game.impl.OrderAndChaosGame;
import game.impl.TicTacToeGame;

/**
 * Game factory - According to the parameters passed in, the corresponding Game object can be generated
 */
public class GameFactory {

    public Game createGame(String gameType){
       if(GameType.TicTacToe.getGameType().equals(gameType)){
           return new TicTacToeGame();
       }else if(GameType.OrderAndChaos.getGameType().equals(gameType)){
           return new OrderAndChaosGame();
       }else{
            return null;
        }
    }


    public void printGameList() {
        GameType[] gameTypes = GameType.values();
        for(GameType gameType : gameTypes){
            System.out.println(gameType);
        }
    }
}
