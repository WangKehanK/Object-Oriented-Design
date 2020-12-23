package game.world;

import game.world.cell.Cell;
import game.world.cell.HeroAndMonsterCell;

/**
 * A game board (grid), build up by all kinds of cells.
 */
public class HeroAndMonsterBoard implements Board{
    private HeroAndMonsterCell[][] heroAndMonsterCells;
    private int boardLength;
    private int teamX;
    private int teamY;

    public HeroAndMonsterBoard(int boardLength) {
        this.boardLength = boardLength;
        heroAndMonsterCells = new HeroAndMonsterCell[boardLength][boardLength];
    }

    @Override
    public void setCell(int i, int j, Cell cell) {
        this.heroAndMonsterCells[i][j] = (HeroAndMonsterCell) cell;
    }

    public boolean isSimple(int x, int y) {
        return heroAndMonsterCells[x][y].isSimple();
    }

    public void print() {
        System.out.println("*********************************");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (i == teamX && j == teamY) {
                    System.out.print("* \u001B[32mH\u001B[0m ");
                } else {
                    System.out.print("* " + heroAndMonsterCells[i][j].printMap() + " ");
                }
            }
            System.out.println("*");
            System.out.println("*********************************");
        }
    }


    public void setTeamPosition(int x, int y) {
        teamX = x;
        teamY = y;
    }

    /**
     * print the operation menu
     */
    public void printMenu() {
        System.out.println("move up(W/w)");
        System.out.println("move left(A/a)");
        System.out.println("move down(S/s)");
        System.out.println("move right(D/d)");
        System.out.println("show information(I/i)");
        System.out.println("show bag(B/b)");

        if (heroAndMonsterCells[teamX][teamY].isMarket()) {
            System.out.println("Market(M/m)");
        }
        System.out.println("quit game(Q/q)");
    }

    public Boolean process(String str) {
        return heroAndMonsterCells[teamX][teamY].process(str);
    }

    /**
     * determine whether the cell is reachable
     *
     * @param newX
     * @param newY
     * @return
     */
    public boolean canMove(Integer newX, Integer newY) {
        if (heroAndMonsterCells[newX][newY].isUnreachable()) {
            return false;
        }
        return true;
    }


    public void moveTeam(Integer newX, Integer newY) {
        teamX = newX;
        teamY = newY;
    }

    @Override
    public Cell get(Integer newX, Integer newY) {
        return heroAndMonsterCells[newX][newY];
    }
}
