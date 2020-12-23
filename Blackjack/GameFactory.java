/**
 * A class that can generate the corresponding game according to the parameters passed in.
 */
public class GameFactory {

    public Game createGame(String gameType){
       if(GameType.Single.getGameType().equals(gameType)){
           return new BlackJackGame(1);
       }else if(GameType.Multi.getGameType().equals(gameType)){
           return new BlackJackGame();
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
