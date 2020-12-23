/**
 * Main - is the main entrance of the game
 */
public class Main {

    public static void main(String[] args) {
        GameFactory gameFactory = new GameFactory();
        while (true) {
            System.out.println("********************  OOP BoardGame   **********************");
            System.out.println("Enter corresponding number to choose the game you want play!");
            System.out.println("************************************************************");
            gameFactory.printGameList();
            System.out.println("Enter 1~3 to pick the game or exit: ");
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
