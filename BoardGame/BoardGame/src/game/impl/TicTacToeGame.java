package game.impl;

import board.impl.TicTacToeBoard;
import game.AbstractBoardGame;
import player.Player;
import util.InputUtil;
import util.NumberUtil;

import java.util.List;

/**
 * TicTacToe Game
 * extends AbstractBoardGame, more details game setting
 */
public class TicTacToeGame extends AbstractBoardGame {


    public TicTacToeGame() {
        this.gameName = "Tic Tac Toe";
        this.team1Name = "1";
        this.team2Name = "2";
    }

    @Override
    protected void initBoard() {
        int size;
        while (true) {
            System.out.println("Please input board size:");
            String teammateNumberStr = InputUtil.getInput();
            if (NumberUtil.isNumber(teammateNumberStr)) {
                int number = Integer.parseInt(teammateNumberStr);
                if (number >= 3) {
                    size = number;
                    break;
                }
            }
            System.out.println("please enter an valid number.");
        }
        victoryPieceNum = size;
        board = new TicTacToeBoard(size);
    }

    @Override
    protected String getPieceType(String teamName, int personNumber, String playerName) {
        if (teamName.equals("1")) {
            return "X";
        } else {
            return "O";
        }
    }

    @Override
    protected void gameOver(String teamName, List<Player> teamList) {
        System.out.println("This game is peace game.");
        for (Player player : teamList) {
            player.addPeaceNum();
        }
    }

    @Override
    protected void gameVictory(String teamName, List<Player> teamList) {
        System.out.println("Congratulations! Team " + teamName + " won the game.");

        for (Player winPlayer : teamList) {
            winPlayer.addVictoryNum();
        }
    }

}