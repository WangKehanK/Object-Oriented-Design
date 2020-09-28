package game;

import board.Board;
import player.Player;
import util.InputUtil;
import util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract board game class implements the game interface and winnable interface. Here contains the basic attributes and basic operations of a board game
 */
public abstract class AbstractBoardGame implements Game, Winnable {
    protected Board board;

    protected int teammateNumber;
    protected List<Player> team1;
    protected List<Player> team2;

    protected String gameName;
    protected String team1Name;
    protected String team2Name;

    protected int lastPutNumber;
    protected String lastPiece;

    protected boolean isVictory;

    protected int victoryPieceNum;


    @Override
    public void initGame() {
        getTeammateNumber();
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        initTeam(team1, team1Name);
        initTeam(team2, team2Name);
    }

    @Override
    public void startGame() {
        System.out.println("Welcome to " + gameName + ".");
        System.out.println();

        while (true) {
            initBoard();
            addGameNumber(team1);
            addGameNumber(team2);
            System.out.println("--------------------------------");
            print();
            while (true) {
                if (putPiece(team1Name, team1)) {
                    break;
                }

                if (putPiece(team2Name, team2)) {
                    break;
                }
            }
            if (!isAgain()) {
                break;
            }
        }

        printSummary();
    }

    @Override
    public void print() {
        board.printBoardNumber();
    }

    @Override
    public boolean isVictory() {
        if (board.checkDirection(lastPutNumber, 1, 1, lastPiece) >= victoryPieceNum ||
                board.checkDirection(lastPutNumber, -1, 1, lastPiece) >= victoryPieceNum ||
                board.checkDirection(lastPutNumber, 0, 1, lastPiece) >= victoryPieceNum ||
                board.checkDirection(lastPutNumber, 1, 0, lastPiece) >= victoryPieceNum) {
            isVictory = true;
            return true;
        }
        return false;
    }


    @Override
    public boolean isOver() {
        return board.isFull() || isVictory();
    }

    protected boolean putPiece(String teamName, List<Player> teamList) {
        int personNumber = NumberUtil.getRandomInt(teamList.size());
        Player player = teamList.get(personNumber);
        String pieceType = getPieceType(teamName, personNumber, player.getName());

        int putNumber = getPutNumber("please Team " + teamName + " Player No." + (personNumber + 1) + " " + player.getName() + " put piece:");

        lastPiece = pieceType;
        lastPutNumber = putNumber;

        board.setBoard(putNumber, pieceType);
        print();
        if (isVictory()) {
            gameVictory(teamName, teamList);

            return true;
        }

        if (isOver()) {
            gameOver(teamName, teamList);

            return true;
        }
        return false;
    }

    protected void addGameNumber(List<Player> team) {
        for (Player player : team) {
            player.addGameNum();
        }
    }


    protected void getTeammateNumber() {
        while (true) {
            System.out.println("Please input teammate number:");
            String teammateNumberStr = InputUtil.getInput();
            if (NumberUtil.isNumber(teammateNumberStr)) {
                teammateNumber = Integer.parseInt(teammateNumberStr);
                break;
            }
            System.out.println("please enter an valid number.");
        }
    }

    protected void initTeam(List<Player> team, String teamName) {
        for (int i = 0; i < teammateNumber; i++) {
            System.out.println("please input team " + teamName + " player No." + (i + 1) + " name:");
            String playerName = InputUtil.getInput();
            Player player = new Player(playerName, teamName, (i + 1));
            team.add(player);
        }
    }

    protected int getPutNumber(String tip) {
        int putNumber;
        while (true) {
            System.out.println(tip);
            String input = InputUtil.getInput();
            if (NumberUtil.isNumber(input)) {
                int number = Integer.parseInt(input);
                if (number > 0 && number <= board.getCellNumber()) {
                    if (board.getBoard(number - 1) != null) {
                        System.out.println("This cell is full");
                        continue;
                    } else {
                        putNumber = number - 1;
                        break;
                    }
                }
            }
            System.out.println("please enter an valid number.");
        }
        return putNumber;
    }

    protected boolean isAgain() {
        System.out.println("---------------------------");
        System.out.println();
        boolean userChoose;
        while (true) {
            System.out.println("continue?(Y/N)");
            String input = InputUtil.getInput();
            if ("Y".equalsIgnoreCase(input)) {
                userChoose = true;
                break;
            } else if ("N".equalsIgnoreCase(input)) {
                userChoose = false;
                break;
            }
        }

        if (userChoose) {
            return true;
        } else {
            return false;
        }
    }

    protected void printSummary() {
        //总结
        System.out.println();
        System.out.println();
        System.out.println("-------Team " + team1Name + "---------");
        for (Player player : team1) {
            System.out.println(player);
        }
        System.out.println("-------Team " + team2Name + "---------");
        for (Player player : team2) {
            System.out.println(player);
        }
    }

    protected abstract void initBoard();

    protected abstract void gameOver(String teamName, List<Player> teamList);

    protected abstract void gameVictory(String teamName, List<Player> teamList);

    protected abstract String getPieceType(String teamName, int personNumber, String playerName);

}
