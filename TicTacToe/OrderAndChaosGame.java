import java.util.List;

/**
 OrderAndChaos - extends AbstractBoardGame, with specific game setting
 */
public class OrderAndChaosGame extends AbstractBoardGame {


    public OrderAndChaosGame() {
        this.gameName = "Order And Chaos";
        this.team1Name = "Order";
        this.team2Name = "Chaos";
        this.victoryPieceNum = 5;
    }

    @Override
    protected void initBoard() {
        board = new OrderAndChaosBoard();
    }

    @Override
    protected String getPieceType(String teamName, int personNumber, String playerName) {
        while (true) {
            String tip = "please Team " + teamName + " Player No." + (personNumber + 1) + " " + playerName + " choose mark type:";
            System.out.println(tip);
            String input = InputUtil.getInput();
            if (input != null && (input.equalsIgnoreCase("X") || input.equalsIgnoreCase("O"))) {
                return input.toUpperCase();
            }
            System.out.println("please enter X or O");
        }
    }

    @Override
    protected void gameOver(String teamName, List<Player> teamList) {
        System.out.println("Congratulations! Team Chaos won the game.");
        for (Player winPlayer : team2) {
            winPlayer.addVictoryNum();
        }
    }

    @Override
    protected void gameVictory(String teamName, List<Player> teamList) {
        System.out.println("Congratulations! Team Order won the game.");
        for (Player winPlayer : team1) {
            winPlayer.addVictoryNum();
        }
    }


}
