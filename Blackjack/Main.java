/**
 * is the main entrance of the game
 */
public class Main {

    public static void main(String[] args) {
        GameFactory gameFactory = new GameFactory();
        while (true) {
            gameFactory.printGameList();
            String gameType = InputUtil.getInput();
            if (GameType.Exit.getGameType().equals(gameType)) {
                System.out.println("Thank You!!!");
                break;
            } else {
                Game game = gameFactory.createGame(gameType);
                if (game == null) {
                    System.out.println("Error type. Try Again");
                } else {
                    game.initGame();
                    game.startGame();
                }
            }
        }
    }
}
