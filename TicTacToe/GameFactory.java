/**
 GameFactory - A class that can generate the corresponding game according to the parameters passed in.
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
